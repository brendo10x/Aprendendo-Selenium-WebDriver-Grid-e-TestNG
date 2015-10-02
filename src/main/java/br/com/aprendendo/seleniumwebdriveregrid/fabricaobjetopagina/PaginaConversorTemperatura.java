package br.com.aprendendo.seleniumwebdriveregrid.fabricaobjetopagina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
//Esta classe tem como objetivo fazer referência a Página Conversor de
//Temperatura ( http://artigo.pe.hu/?pg=conv_temperatura.php ) da aplicação  
//web, a qual será utilizada nos testes. 

public class PaginaConversorTemperatura {

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="valuefromtemperature1" na página web
	@FindBy(how = How.ID, using = "valuefromtemperature1")
	private WebElement valorEntrada;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="selectfromtemperature1" na página web
	@FindBy(how = How.ID, using = "selectfromtemperature1")
	private WebElement webSelectDe;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="selecttotemperature1" na página web
	@FindBy(how = How.ID, using = "selecttotemperature1")
	private WebElement webSelectPara;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="valuetotemperature1" na página web
	@FindBy(how = How.ID, using = "valuetotemperature1")
	private WebElement resultado;

	// WebDriver
	private WebDriver driver;

	// Implementação do construtor
	public PaginaConversorTemperatura(WebDriver driver) {
		// Atribuição do parâmetro que tem instância do WebDriver, para o objeto
		// WebDriver que tem o escopo desta classe
		this.driver = driver;
		// Permite que os atributos mapeados nesta classe com @FindBy sejam
		// carregados com os elementos html correspondente
		PageFactory.initElements(this.driver, this);
		// Carrega e inicia o navegador com o endereço web
		this.driver.get("http://artigo.pe.hu/?pg=conv_temperatura.php");
	}

	public void preencherValorDeEntrada(String i) {
		// Comando que envia valores para o elemento html
		valorEntrada.sendKeys(i);
	}

	public WebElement obterValorDeEntrada() {
		// retorna objeto WebElement
		return valorEntrada;
	}

	public Select obterSelectDeTemperatura() {
		// Retorna um objeto Select que faz referência ao elemento html Select
		// na página web
		return new Select(webSelectDe);
	}

	public Select obterSelectParaTemperatura() {
		// Retorna um objeto Select que faz referência ao elemento html Select
		// na página web
		return new Select(webSelectPara);
	}

	public WebElement obterResultadoTemperatura() {
		// retorna objeto WebElement
		return resultado;
	}

}