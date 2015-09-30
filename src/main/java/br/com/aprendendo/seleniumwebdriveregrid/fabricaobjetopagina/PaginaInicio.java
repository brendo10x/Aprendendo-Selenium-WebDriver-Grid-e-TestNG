package br.com.aprendendo.seleniumwebdriveregrid.fabricaobjetopagina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Esta classe tem como objetivo fazer referência a Página Início (http://artigo.pe.hu)
//da aplicação web, a qual será utilizada nos testes.                                  
public class PaginaInicio {

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="conversor" na página web
	@FindBy(how = How.ID, using = "conversor")
	private WebElement menuOpcaoConversor;

	// Mapeamento entre o objeto WebElement com o elemento html que tem o
	// id="temperatura" na página web
	@FindBy(how = How.ID, using = "temperatura")
	private WebElement menuOpcaoConversorTemperatura;

	// WebDriver
	private WebDriver driver;

	// Implementação do construtor
	public PaginaInicio(WebDriver driver) {
		// Atribuição do parâmetro que tem instância do WebDriver, para o objeto
		// Atribuição do WebDriver corrente
		this.driver = driver;

		// Permite que os atributos mapeados nesta classe com @FindBy sejam
		// carregado com os elementos html correspondente
		PageFactory.initElements(driver, this);

		// Carrega e inicia o navegador com o endereço web
		this.driver.get("http://artigo.pe.hu");
	}

	public PaginaConversorTemperatura irParaPaginaConversorTemperatura() {

		// Inicializa o objeto Actions para uso com o WebDriver corrente
		Actions acoesAvancadasDeUsuario = new Actions(driver);

		// Inicializa o objeto WebDriverWait para uso com o Webdriver corrente
		// No construtor da classe abaixo além de ser definido o Webdriver, foi
		// definido também o tempo de espera, ou seja 10 segundos
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Clique no menu Conversor na aplicação web
		acoesAvancadasDeUsuario.moveToElement(menuOpcaoConversor).click()
				.perform();

		// Depois, espere no máximo 10 segundos(se passar disso lance um
		// exception) até que os itens do menu Conversor apareçam
		wait.until(ExpectedConditions
				.visibilityOf(menuOpcaoConversorTemperatura));

		// Quando os itens do menu Conversor aparecerem, clique no item
		// Conversor
		// de Temperatura
		acoesAvancadasDeUsuario.moveToElement(menuOpcaoConversorTemperatura)
				.click().perform();

		// As ações acima ocorrem em tempo real na aplicação web

		// E para satisfazer o retorno do método, retorne o objeto da classe
		// PaginaConversorTemperatura com o driver corrente
		return new PaginaConversorTemperatura(driver);

	}
}