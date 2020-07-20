package com.br.transporteapi.service;

import com.br.transporteapi.controller.form.VeiculoForm;
import com.br.transporteapi.error.exception.ErroAoCadastrarException;
import com.br.transporteapi.error.exception.ReferenciaNaoEncontradaException;
import com.br.transporteapi.model.Linha;
import com.br.transporteapi.model.Veiculo;
import com.br.transporteapi.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService implements AbstractService<Veiculo, VeiculoForm> {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private LinhaService linhaService;

    public Page<Veiculo> findAllByLinha_Id(Pageable pageable, Long idLinha) {
        Linha linha = this.linhaService.findById(idLinha);
        return this.veiculoRepository.findAllByLinha_Id(pageable, idLinha);
    }

    @Override
    public List<Veiculo> findAll() {
        return this.veiculoRepository.findAll();
    }

    @Override
    public Page<Veiculo> findAll(Pageable pageable) {
        return this.veiculoRepository.findAll(pageable);
    }

    @Override
    public Veiculo findById(Long id) {
        Optional<Veiculo> optionalVeiculo = this.veiculoRepository.findById(id);

        if (!optionalVeiculo.isPresent())
            throw new ReferenciaNaoEncontradaException("Não foi encontrado nenhum veículo com essa referência");

        return optionalVeiculo.get();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Veiculo veiculo = this.findById(id);

        this.veiculoRepository.deleteById(veiculo.getId());
    }

    @Override
    @Transactional
    public Veiculo save(VeiculoForm form) {
        Veiculo veiculo = this.veiculoRepository.save(form.convert(linhaService));

        if (veiculo == null) throw new ErroAoCadastrarException("Erro ao salvar o veículo");
        else return veiculo;
    }

    @Override
    @Transactional
    public Veiculo edit(Long id, VeiculoForm veiculoForm) {
        Veiculo veiculo = this.findById(id);
        Linha linha = this.linhaService.findById(veiculoForm.getIdLinha());

        veiculo.setLinha(linha);
        veiculo.setModelo(veiculoForm.getModelo());
        veiculo.setNome(veiculoForm.getNome());

        return veiculo;
    }
}
