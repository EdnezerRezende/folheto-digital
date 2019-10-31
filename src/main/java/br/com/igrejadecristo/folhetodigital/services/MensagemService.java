package br.com.igrejadecristo.folhetodigital.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejadecristo.folhetodigital.dto.MensagemDTO;
import br.com.igrejadecristo.folhetodigital.dto.MensagemNewDTO;
import br.com.igrejadecristo.folhetodigital.entidades.Mensagem;
import br.com.igrejadecristo.folhetodigital.respositories.MensagemRepository;

@Service
public class MensagemService {

	@Autowired
	private MensagemRepository mensagemDao;
	
	public List<Mensagem> buscarTodos() {
		List<Mensagem> mensagens = mensagemDao.findAllByOrderByDataCriado();
		return mensagens;
	}
	
	public MensagemDTO buscarPorMensagem(Integer idMensagem) {
		Mensagem mensagem = mensagemDao.findById(idMensagem).get();
		return new MensagemDTO(mensagem);
	}
	
	@Transactional
	public Mensagem salvarMensagem(MensagemNewDTO dto) {
		if (dto.getId() != null) {
			Boolean existeMensagem = mensagemDao.existsById(dto.getId());
			if (!existeMensagem) {
				throw new RuntimeException("Ocorreu um erro, mensagem não existe no sistema!");
			}
		}
		
		Mensagem mensagem = new Mensagem(dto.getId(),
				dto.getMensagem(), 
				dto.getAutor(), LocalDate.now(),  dto.getTitulo());
		return mensagemDao.save(mensagem);
	}
	
	@Transactional
	public void deletarMensagem(Integer idMensagem) {
		
		mensagemDao.deleteById(idMensagem);
	}
}
