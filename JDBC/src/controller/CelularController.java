package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.CelularDAO;
import model.Celular;
import view.CelularView;

public class CelularController {
	private CelularView view = new CelularView();

	public CelularController() {
		
		MouseListener ouvinte2 = new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == view.getTabela()) {
					//tabela
		            int linha = view.getTabela().getSelectedRow();
		            
		            int id = (int) view.getTabela().getValueAt(linha, 0);
		            String marca = (String) view.getTabela().getValueAt(linha, 1);
		            String modelo = (String) view.getTabela().getValueAt(linha, 2);
		            String memoria = (String) view.getTabela().getValueAt(linha, 3);
		            view.getCampoIdAlterar().setText( String.format("%d", id) );
		            view.getCampoMarcaAlterar().setText(marca);
		            view.getCampoModeloAlterar().setText(modelo);
		            view.getCampoMemoriaAlterar().setText(memoria);
		            view.getBotaoAlterar().setEnabled(true);
		            view.getBotaoDeletar().setEnabled(true);
				}
			}
		};

		ActionListener ouvinte = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == view.getBotaoSalvar()) {
					// salvar
					Celular celular = new Celular();
					celular.setMarca(view.getCampoMarca().getText());
					celular.setModelo(view.getCampoModelo().getText());
					celular.setMemoria(view.getCampoMemoria().getText());
					
					CelularDAO dao = new CelularDAO();
					dao.salvar(celular);
					
					JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
					List<Celular> listaCelulares = dao.listar();
					view.carregarTabela(listaCelulares,ouvinte2);
					view.limparCampos();
				}else
					if(e.getSource() == view.getBotaoAlterar()) {
						// alterar
						Celular celular = new Celular();
						String id = view.getCampoIdAlterar().getText();
						if(!id.equals("")) {
							celular.setId(Integer.parseInt(id));
							celular.setMarca(view.getCampoMarcaAlterar().getText());
							celular.setModelo(view.getCampoModeloAlterar().getText());
							celular.setMemoria(view.getCampoMemoriaAlterar().getText());
							
							CelularDAO dao = new CelularDAO();
							dao.alterar(celular);
							
							JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
							List<Celular> listaCelulares = dao.listar();
							view.carregarTabela(listaCelulares,ouvinte2);
							view.limparCampos();
						}
					}else
						if(e.getSource() == view.getBotaoDeletar()) {
							// deletar
							String id = view.getCampoIdAlterar().getText();
							if(!id.equals("")) {
								CelularDAO dao = new CelularDAO();
								dao.deletar(Integer.parseInt(id));
								JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
								List<Celular> listaCelulares = dao.listar();
								view.carregarTabela(listaCelulares,ouvinte2);
								view.limparCampos();
							}
						}

			}
		};

		view.configurarComponentes(ouvinte);

		CelularDAO dao = new CelularDAO();
		List<Celular> listaCelulares = dao.listar();
		view.carregarTabela(listaCelulares,ouvinte2);
	}
}
