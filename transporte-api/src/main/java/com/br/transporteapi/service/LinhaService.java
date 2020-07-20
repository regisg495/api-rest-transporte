package com.br.transporteapi.service;

import com.br.transporteapi.controller.form.LinhaForm;
import com.br.transporteapi.error.exception.ErroAoCadastrarException;
import com.br.transporteapi.error.exception.NomeDaLinhaJaExisteException;
import com.br.transporteapi.error.exception.ReferenciaNaoEncontradaException;
import com.br.transporteapi.model.Linha;
import com.br.transporteapi.model.Parada;
import com.br.transporteapi.repository.LinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LinhaService implements AbstractService<Linha, LinhaForm> {

    @Autowired
    private LinhaRepository linhaRepository;

    @Autowired
    private ParadaService paradaService;

    public Page<Linha> findAllByParadasId(Pageable pageable, Long idParada) {
        return this.linhaRepository.findAllByParadasId(pageable, idParada);
    }

    @Override
    public List<Linha> findAll() {
        return this.linhaRepository.findAll();
    }

    @Override
    public Linha findById(Long id) {
        Optional<Linha> optionalLinha = this.linhaRepository.findById(id);

        if (!optionalLinha.isPresent())
            throw new ReferenciaNaoEncontradaException("Não foi encontrada uma linha com essa referência");

        return optionalLinha.get();
    }

    @Override
    public Page<Linha> findAll(Pageable pageable) {
        return this.linhaRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Linha linha = this.findById(id);

        this.linhaRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class})
    public Linha save(LinhaForm linhaForm) {
        if (this.linhaRepository.existsByNome(linhaForm.getNome()))
            throw new NomeDaLinhaJaExisteException("Já existe uma linha cadastrada com esse nome");

        List<Parada> paradas = new ArrayList<>();
        if (!linhaForm.getIdParadas().isEmpty()) {
            for (Long idParada : linhaForm.getIdParadas()) {
                Parada parada = this.paradaService.findById(idParada);
                paradas.add(parada);
            }
        }

        Linha linha = linhaForm.convert();
        linha.setParadas(paradas);
        linha = this.linhaRepository.save(linha);

        if (linha == null) throw new ErroAoCadastrarException("Erro ao salvar a linha");
        else return linha;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Linha edit(Long id, LinhaForm linhaForm) {
        Linha linha = this.findById(id);

        Optional<Linha> linhaOptional = this.linhaRepository.findByNome(linhaForm.getNome());

        if (linhaOptional.isPresent() && !linhaOptional.get().getId().equals(linha.getId()))
            throw new NomeDaLinhaJaExisteException("Já existe uma linha cadastrada com esse nome");

        List<Parada> paradas = new ArrayList<>();

        for (Long idParada : linhaForm.getIdParadas()) {
            Parada parada = this.paradaService.findById(idParada);
            paradas.add(parada);
        }

        linha.setParadas(paradas);
        linha.setNome(linhaForm.getNome());

        return linha;

    }

}
