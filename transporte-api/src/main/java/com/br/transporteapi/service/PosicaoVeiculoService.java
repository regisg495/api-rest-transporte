package com.br.transporteapi.service;

import com.br.transporteapi.controller.form.PosicaoVeiculoForm;
import com.br.transporteapi.error.exception.ErroAoCadastrarException;
import com.br.transporteapi.error.exception.PosicaoVeiculoCadastradaException;
import com.br.transporteapi.error.exception.ReferenciaNaoEncontradaException;
import com.br.transporteapi.model.PosicaoVeiculo;
import com.br.transporteapi.model.Veiculo;
import com.br.transporteapi.repository.PosicaoVeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PosicaoVeiculoService implements AbstractService<PosicaoVeiculo, PosicaoVeiculoForm> {

    @Autowired
    private PosicaoVeiculoRepository posicaoVeiculoRepository;

    @Autowired
    private VeiculoService veiculoService;

    @Override
    public List<PosicaoVeiculo> findAll() {
        return this.posicaoVeiculoRepository.findAll();
    }

    @Override
    public Page<PosicaoVeiculo> findAll(Pageable pageable) {
        return this.posicaoVeiculoRepository.findAll(pageable);
    }

    @Override
    public PosicaoVeiculo findById(Long idVeiculo) {
        Optional<PosicaoVeiculo> optionalPosicaoVeiculo = this.posicaoVeiculoRepository.findById(idVeiculo);

        if (!optionalPosicaoVeiculo.isPresent())
            throw new ReferenciaNaoEncontradaException("Não foi encontrado nenhum veículo com essa referência");

        return optionalPosicaoVeiculo.get();
    }

    @Override
    @Transactional
    public void deleteById(Long idVeiculo) {
        PosicaoVeiculo posicaoVeiculo = this.findById(idVeiculo);
        this.posicaoVeiculoRepository.deleteById(posicaoVeiculo.getVeiculo().getId());
    }

    @Override
    @Transactional
    public PosicaoVeiculo save(PosicaoVeiculoForm posicaoVeiculoForm) {
        Veiculo veiculo = this.veiculoService.findById(posicaoVeiculoForm.getIdVeiculo());

        if (this.posicaoVeiculoRepository.findByLatitudeAndLongitude(posicaoVeiculoForm.getCoordenadasForm().getLatitude(),
                posicaoVeiculoForm.getCoordenadasForm().getLongitude()).isPresent())
            throw new PosicaoVeiculoCadastradaException(
                    "Já existe um veículo cadastrado nessa posição");

        PosicaoVeiculo posicaoVeiculo = posicaoVeiculoForm.convert();
        posicaoVeiculo.setVeiculo(veiculo);
        this.posicaoVeiculoRepository.save(posicaoVeiculo);

        if (posicaoVeiculo == null) throw new ErroAoCadastrarException("Erro ao salvar a posição");
        else return posicaoVeiculo;
    }

    @Override
    @Transactional
    public PosicaoVeiculo edit(Long idVeiculo,
                               PosicaoVeiculoForm posicaoVeiculoForm) {
        PosicaoVeiculo posicaoVeiculo = this.findById(idVeiculo);

        Optional<PosicaoVeiculo> posicaoVeiculoOptional = this.posicaoVeiculoRepository.findByLatitudeAndLongitude(
                posicaoVeiculoForm.getCoordenadasForm().getLatitude(), posicaoVeiculoForm.getCoordenadasForm().getLongitude());

        if (posicaoVeiculoOptional.isPresent() && !posicaoVeiculoOptional.get().getId().equals(idVeiculo))
            throw new PosicaoVeiculoCadastradaException("Já existe um veículo cadastrado nessa localização");

        posicaoVeiculo.setLatitude(posicaoVeiculoForm.getCoordenadasForm().getLatitude());
        posicaoVeiculo.setLongitude(posicaoVeiculoForm.getCoordenadasForm().getLongitude());

        return posicaoVeiculo;
    }
}
