package br.com.aprendendo.seleniumwebdriveregrid.fabricaobjetopagina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
//Esta classe tem como objetivo fazer referência a Página Trabalhe Conosco 
//(http://artigo.pe.hu/?pg=trabalhe_conosco.php) da aplicação web, a qual 
//será utilizada nos testes. 

public class PaginaTrabalheConosco {

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="nome" na página web
	@FindBy(how = How.ID, using = "nome")
	private WebElement nome;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="email" na página web
	@FindBy(how = How.ID, using = "email")
	private WebElement email;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="opcaoMasculino" na página web
	@FindBy(how = How.ID, using = "opcaoMasculino")
	private WebElement opcaoSexoMasculino;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="checkIngles" na página web
	@FindBy(how = How.ID, using = "checkIngles")
	private WebElement checkIdiomaIngles;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="selectOpcaoVaga" na página web
	@FindBy(how = How.ID, using = "selectOpcaoVaga")
	private WebElement selectOpcaoVaga;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="arquivoCurriculo" na página web
	@FindBy(how = How.ID, using = "arquivoCurriculo")
	private WebElement arquivoCurriculo;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="botaoEnviar" na página web
	@FindBy(how = How.ID, using = "botaoEnviar")
	private WebElement botaoEnviar;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="msgAposEnvio" na página web
	@FindBy(how = How.ID, using = "msgAposEnvio")
	private WebElement msgAposEnvio;

	// WebDriver
	private WebDriver driver;

	// Implementação do construtor
	public PaginaTrabalheConosco(WebDriver driver) {
		// Atribuição do parâmetro que tem instância do WebDriver, para o objeto
		// WebDriver que tem o escopo desta classe
		this.driver = driver;
		// Permite que os atributos mapeados nesta classe com @FindBy sejam
		// carregados com os elementos html correspondente
		PageFactory.initElements(this.driver, this);
		// Carrega e inicia o navegador com o endereço web
		this.driver.get("http://artigo.pe.hu/?pg=trabalhe_conosco.php");
	}

	public void preencherNome(String nomeValor) {
		// Comando que envia valores para o elemento html
		nome.sendKeys(nomeValor);
	}

	public void preencherEmail(String emailValor) {
		// Comando que envia valores para o elemento html
		email.sendKeys(emailValor);
	}

	public void marcarRadioButtonSexoMasculino() {
		// Comando que clica no elemento html
		opcaoSexoMasculino.click();

	}

	public void marcarCkeckBoxIdiomaIngles() {
		// Comando que clica no elemento html
		checkIdiomaIngles.click();
	}

	public void selecionarVaga(String vaga) {
		// Atribuição de objeto Select que faz referência ao elemento html
		// Select
		Select selOpcaoVaga = new Select(selectOpcaoVaga);
		// Comando que marca a opção do select que tem o texto x visível na
		// página web
		selOpcaoVaga.selectByVisibleText(vaga);
	}

	public void selecionarArquivoCurriculo(String urlAbsolutaDoArquivoPdf) {
		// Comando que envia valores para o elemento html
		arquivoCurriculo.sendKeys(urlAbsolutaDoArquivoPdf);
	}

	public void enviarMensagemDeTrabalheConosco() {
		// Comando que clica no elemento html
		botaoEnviar.click();
	}

	public String obterMensagemAposEnvio() {
		// Comando retorna o texto visível do elemento html
		return msgAposEnvio.getText();
	}
}