package com.br.transporteapi.service;

import com.br.transporteapi.controller.DTO.ParadaDistanciaDTO;
import com.br.transporteapi.controller.form.ParadaForm;
import com.br.transporteapi.error.exception.ErroAoCadastrarException;
import com.br.transporteapi.error.exception.NomeDaParadaJaExisteException;
import com.br.transporteapi.error.exception.PosicaoParadaCadastradaException;
import com.br.transporteapi.error.exception.ReferenciaNaoEncontradaException;
import com.br.transporteapi.model.Parada;
import com.br.transporteapi.model.UnidadeDistancia;
import com.br.transporteapi.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ParadaService implements AbstractService<Parada, ParadaForm> {

    @Autowired
    private ParadaRepository paradaRepository;

    public List<ParadaDistanciaDTO> findAllParadasProximas(Double latitude, Double longitude, Double areaCoberta, String unidadeDistancia) {
        UnidadeDistancia unidade = UnidadeDistancia.fromUnidade(unidadeDistancia);
        if (unidade.getUnidade().equals("km"))
            return this.paradaRepository.findAllParadasProximasInKm(latitude, longitude, areaCoberta);
        else if (unidade.getUnidade().equals("m"))
            return this.paradaRepository.findAllParadasProximasInMeters(latitude, longitude, areaCoberta);
        else return this.paradaRepository.findAllParadasProximasInMiles(latitude, longitude, areaCoberta);
    }

    @Override
    public List<Parada> findAll() {
        return this.paradaRepository.findAll();
    }

    @Override
    public Page<Parada> findAll(Pageable pageable) {
        return this.paradaRepository.findAll(pageable);
    }

    @Override
    public Parada findById(Long id) {
        Optional<Parada> paradaOptional = this.paradaRepository.findById(id);

        if (!paradaOptional.isPresent())
            throw new ReferenciaNaoEncontradaException("Não foi encontrada uma parada com essa referência");

        return paradaOptional.get();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Parada parada = this.findById(id);

        this.paradaRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Parada save(ParadaForm paradaForm) {

        if (this.paradaRepository.findByNome(paradaForm.getNome()).isPresent())
            throw new NomeDaParadaJaExisteException("Já existe uma parada com esse nome");

        if(!this.paradaRepository.findByLatitudeAndLongitude(paradaForm.getCoordenadasForm()
        .getLatitude(), paradaForm.getCoordenadasForm().getLongitude()).isEmpty())
            throw new PosicaoParadaCadastradaException("Já existe uma parada cadastrada nessa localização");

        Parada parada = this.paradaRepository.save(paradaForm.convert());

        if (parada == null)
            throw new ErroAoCadastrarException("Erro ao cadastrar a parada");
        else return parada;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Parada edit(Long id, ParadaForm paradaForm) {

        Optional<Parada> paradaOptional = this.paradaRepository.findByNome(paradaForm.getNome());

        Parada parada = this.findById(id);

        if (paradaOptional.isPresent() && !paradaOptional.get().getId().equals(parada.getId()))
            throw new NomeDaParadaJaExisteException("Já existe uma parada com esse nome");

        List<Parada> paradas = this.paradaRepository.findByLatitudeAndLongitude(paradaForm.getCoordenadasForm().getLatitude(),
                paradaForm.getCoordenadasForm().getLongitude());

        for(Parada p : paradas) {
            if(!p.getId().equals(parada.getId()))
                throw new PosicaoParadaCadastradaException("Já existe uma parada cadastrada nessa posição");
        }


            parada.setNome(paradaForm.getNome());
        parada.setLatitude(paradaForm.getCoordenadasForm().getLatitude());
        parada.setLongitude(paradaForm.getCoordenadasForm().getLongitude());

        return parada;
    }

}
