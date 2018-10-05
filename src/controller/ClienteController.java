package controller;//nome do pacote 

//importa��es necessarias

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import factory.JPAFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Cliente;
import repository.ClienteRepository;

//classe controle 
public class ClienteController implements Initializable {
	// lista de componentes do aquivo.fxml
	private Cliente cliente;
	@FXML
	private Label lbAniversario;

	@FXML
	private DatePicker dpAniversario;

	@FXML
	private TextField tfNome, tfCpf, tfEndereco, tfEmail;

	@FXML
	private Button btLimpar, btIncluir, btExcluir, btAlterar;

	@FXML
	private TableView<Cliente> tvClientes;

	@FXML
	private TableColumn<Cliente, Integer> tcIdCliente;

	@FXML
	private TableColumn<Cliente, String> tcCpfCliente;

	@FXML
	private TableColumn<Cliente, String> tcNomeCliente;

	@FXML
	private TableColumn<Cliente, String> tcEnderecoCliente;

	@FXML
	private TableColumn<Cliente, String> tcEmailCliente;
	@FXML
	private TextField tfPesquisar;

	@FXML
	private TabPane tpAbas;

	// interface initialize obriaga a implementar
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// setando o focus/cursor no textfield nome
		tfNome.requestFocus();
		// configurando as colunas da tabela conforme os atributos da classe cliente
		tcIdCliente.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcCpfCliente.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tcEnderecoCliente.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcEmailCliente.setCellValueFactory(new PropertyValueFactory<>("email"));

		// para incluir partindo do botao incluir
		// btIncluir.setOnAction(event -> System.out.println(tfNome.getText()));
		// btLimpar.setOnAction(event -> {
		// tfCpf.setText("");
		// tfNome.setText("");
		// tfEmail.setText("");
		// tfEndereco.setText("");
		// });

	}

	// metodo que realiza a a��o do botao pesquisar
	@FXML
	void handlePesquisar(ActionEvent event) {
	
		
		// System.out.println("pesquisar");// print utilizado para testar botao antes de
		// fazer sql
		//////////////////////////////////////////////////////////////////////////////////////////
		// faz conecao com banco
//		EntityManager em = JPAFactory.geEntityManager();
//		// sql que busca pelo nome considerando letras maiusculas e minusculas informado
//		// no textbox da busca
//		Query query = em.createQuery("SELECT c FROM Cliente c WHERE lower(c.nome)  like lower( :nome )");
//		// pegando o valor inormado no campo da busca
	

//		query.setParameter("nome", "%" + tfPesquisar.getText() + "%");
//		// cria lista de clientes com o retorno da consulta sql
//		List<Cliente> lista = query.getResultList();
//		// verifica se a lista est� vazia ou nula, caso esteja emite um alerta,
//		// informando que nada foi encontrado
//		

		ClienteRepository repository= new ClienteRepository(JPAFactory.geEntityManager());
		List<Cliente> lista =repository.getClientes(tfPesquisar.getText());
		
		//if (lista == null || lista.isEmpty()) {
		if(lista.isEmpty()) {
		// alerta do tipo imforma��o
		Alert alerta = new Alert(AlertType.INFORMATION);
			// setando titulo da janela
			alerta.setTitle("Informa��o");
			// titulo da mensagem no caso nao teve
			alerta.setHeaderText(null);
			// mensagem para usuario
			alerta.setContentText("A consulta n�o retornou dados.");
			// mosta a janela/mensagem
			alerta.show();
		}
		// caso enconte algum resgistro eh listado
		tvClientes.setItems(FXCollections.observableList(lista));
	}

	// evento captura e verifica cliques do mouse
	@FXML
	void handleMouseClicked(MouseEvent event) {
		// verificando se eh o botao principal, ou seja, se foi clicado com bot�o do
		// esquerdo do mouse
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			// verificando se foram dois cliques.
			if (event.getClickCount() == 2) {
				// se sim pega os dados do registro clicado
				cliente = tvClientes.getSelectionModel().getSelectedItem();
				tfCpf.setText(cliente.getCpf());
				tfNome.setText(cliente.getNome());
				tfEndereco.setText(cliente.getEndereco());
				tfEmail.setText(cliente.getEmail());
				dpAniversario.setValue(cliente.getDpAniversario());
				// depois dos dois cliques � carregada a aba cadastro com os campos ja
				// preenchidos para edicao
				tpAbas.getSelectionModel().select(0);
				// setando o focus/curso no campo nome
				tfNome.requestFocus();
				// chama o metodo atualizar botoes para desabilitar algum botao
				atualizarBotoes();
			}

		}
	}

	// evento para alterar os dados puchados do db, ou seja, � o edit
	@FXML
	void handleAlterar(ActionEvent event) {

		// setando os dados puchado do bd
		cliente.setCpf(tfCpf.getText());
		cliente.setNome(tfNome.getText());
		cliente.setEndereco(tfEndereco.getText());
		cliente.setEmail(tfEmail.getText());
		cliente.setDpAniversario(dpAniversario.getValue());

		ClienteRepository repository = new ClienteRepository(JPAFactory.geEntityManager());
		repository.getEntityManeger().getTransaction().begin();
		repository.save(cliente);
		repository.getEntityManeger().getTransaction().commit();
		repository.getEntityManeger().close();

		// faz a conex�o com bd
//		EntityManager em = JPAFactory.geEntityManager();
//		// Iniciando a transa��o com bd
//		em.getTransaction().begin();
//		// altera o registro existente no bd
//		em.merge(cliente);
//		// salvar e finaliza a transicao
//		em.getTransaction().commit();
//		em.close();// fecha conexao com bd
//		// alerta do tipo imforma��o

		Alert alerta = new Alert(AlertType.INFORMATION);
		// setando titulo da janela
		alerta.setTitle("Informa��o");
		// titulo da mensagem no caso nao teve
		alerta.setHeaderText(null);
		// mensagem para usuario
		alerta.setContentText("Dados atualizados com sucesso!");
		// mosta a janela/mensagem
		alerta.show();
		// chama o metodo para limpar campos
		handleLimpar(event);
	}

	// evento que exlui os dados informados nos textbox do bd, ou seja, � o delete

	@FXML
	void handleExcluir(ActionEvent event) {
		// faz a conex�o com bd
		// EntityManager em = JPAFactory.geEntityManager();

		ClienteRepository repository = new ClienteRepository(JPAFactory.geEntityManager());
		repository.getEntityManeger().getTransaction().begin();
		repository.remove(cliente);
		repository.getEntityManeger().getTransaction().commit();
		repository.getEntityManeger().close();

//		// Iniciando a transa��o com o bd
//		em.getTransaction().begin();
//		// altera o registro existene no bd
//		cliente = em.merge(cliente);
//		// apaga do db o registro informado
//		em.remove(cliente);
//		// salvar e finaliza a transicao
//		em.getTransaction().commit();
//		// fechar conexao com bd
//		em.close();

		// alerta do tipo imforma��o
		Alert alerta = new Alert(AlertType.INFORMATION);
		// setando titulo da janela
		alerta.setTitle("Informa��o");
		// titulo da mensagem no caso nao teve
		alerta.setHeaderText(null);
		// mensagem para usuario
		alerta.setContentText("Cliente excluido com sucesso!");
		// mosta a janela/mensagem
		alerta.show();
		// chama o metodo para limpar campos
	
		handleLimpar(event);
	}

	@FXML
	void handleIncluir(ActionEvent event) {

		cliente = new Cliente(tfCpf.getText(), tfNome.getText(), tfEndereco.getText(), tfEmail.getText(),
				dpAniversario.getValue());

		ClienteRepository repository = new ClienteRepository(JPAFactory.geEntityManager());

		// ClienteRepository repository = new ClienteRepository2(em);// aqui vamos usar
		// mesma transa��o para inseir dois tabelas diferentes

		// Iniciando a transa��o
		// em.getTransaction().begin();
		repository.getEntityManeger().getTransaction().begin();
		repository.save(cliente);
		// inserindo novo registro no bd
		// em.persist(cliente);
		// salvar e finaliza a transicao
		// em.getTransaction().commit();
		repository.getEntityManeger().getTransaction().commit();
		repository.getEntityManeger().close();
		// em.close();
		// chama o metodo para limpar campos
		handleLimpar(event);

		// alerta do tipo imforma��o
		Alert alerta = new Alert(AlertType.INFORMATION);
		// setando titulo da janela
		alerta.setTitle("Informa��o");
		// titulo da mensagem no caso nao teve
		alerta.setHeaderText(null);
		// mensagem para usuario
		alerta.setContentText("Cliente cadastrado com sucesso!");
		// mosta a janela/mensagem
		alerta.show();

	}

	// metodo que limpa os campos
	@FXML
	void handleLimpar(ActionEvent event) {
		// setando os campos em branco, ou seja, limpa os valores informados
		tfCpf.setText("");
		tfNome.setText("");
		tfEmail.setText("");
		dpAniversario.setValue(null);

		tfEndereco.setText("");
		// limpando as informacoes do cliente
		cliente = new Cliente();
		// chama o metodo atualizar botoes para desabilitar algum botao
		atualizarBotoes();

	}

	// metodo que gerencia a visualizadao dos botoes
	private void atualizarBotoes() {
		btIncluir.setDisable(cliente.getId() != null);
		btAlterar.setDisable(cliente.getId() == null);
		btExcluir.setDisable(cliente.getId() == null);
	}
}
