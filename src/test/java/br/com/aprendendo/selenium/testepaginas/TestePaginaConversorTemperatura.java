package br.com.aprendendo.selenium.testepaginas;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.aprendendo.seleniumwebdriveregrid.apiconversor.ConversorTemperatura;
import br.com.aprendendo.seleniumwebdriveregrid.config.TipoDriver;
import br.com.aprendendo.seleniumwebdriveregrid.fabricaobjetopagina.PaginaConversorTemperatura;
import br.com.aprendendo.seleniumwebdriveregrid.fabricaobjetopagina.PaginaInicio;
//Esta classe tem como objetivo executar os testes funcionais na página conversor temperatura
//(http://artigo.pe.hu/?pg=conv_temperatura.php) da aplicação web

public class TestePaginaConversorTemperatura {

	// Declaração das variáveis
	private Select selectDe;
	private Select selectPara;
	private WebElement resultado;
	private WebElement entrada;
	private WebDriver driver;
	private String resultadoValor;
	private String resultadoCalculado;

	// Primeiro método a ser chamado na classe, pois está anotado com
	// @BeforeClass do TestNG
	@BeforeClass
	public void inicializarDriver() throws Exception {

		// Atribuição da instância do Enum do Tipo Chrome
		TipoDriver selecionadoTipoDriver = TipoDriver.CHROME;

		// Atribuição das capacidades para usar o tipo driver(Chrome)
		DesiredCapabilities desiredCapabilities = selecionadoTipoDriver
				.obterDesiredCapabilities();

		// Atribuição do WebDriver remoto, com as definições de que os testes
		// funcionais desta classe serão executados no navegador Google Chrome
		// do Node http://192.168.0.2:5555/wd/hub (2º máquina virtual usando
		// Linux)
		driver = selecionadoTipoDriver.obterObjetoWebDriverRemoto(
				desiredCapabilities, Platform.LINUX,
				"http://192.168.0.2:5555/wd/hub");

		// Descomente este trecho de código e comente o de cima caso deseje
		// testar esta classe no seu computador.
		/**
		 * driver =
		 * selectedDriverType.obterObjetoWebDriver(desiredCapabilities);
		 */

	}

	// Último método que executa, pois está anotado com @AfterClass do TestNG
	@AfterClass
	public void fecharDriver() {
		// Verifica se o driver é diferente null
		if (null != driver) {
			// Se for, fecha o driver, ou seja o navegador que está em execução
			// será fechado
			driver.quit();
		}
	}

	// Definição de parâmetro que este método receberá, a partir do arquivo do
	// tesng.xml do TestNG declarado neste projeto
	@Parameters({ "entradaParametro" })
	// Executa o método como teste
	@Test
	public void testeCalcularConversorTemperatura(String entradaParametro)
			throws Exception {
		// Este teste executa como se fosse passos de usuário
		// Inicia a página inicial
		PaginaInicio paginaInicio = new PaginaInicio(driver);

		// Depois vai, da página inicial até a página Converso de Temperatura
		PaginaConversorTemperatura paginaConversorTemperatura = paginaInicio
				.irParaPaginaConversorTemperatura();

		// Estando na página Conversor de temperatura
		// Preenche o campo com o valor de entrada
		paginaConversorTemperatura.preencherValorDeEntrada(entradaParametro);

		// Atribui o valor de entrada a partir da referência do elemento html
		entrada = paginaConversorTemperatura.obterValorDeEntrada();
		// Atribui a referência do elemento html Select
		selectDe = paginaConversorTemperatura.obterSelectDeTemperatura();
		// Atribui a referência de outro elemento html Select
		selectPara = paginaConversorTemperatura.obterSelectParaTemperatura();
		// Por fim, atribui do valor do resultado a partir da referência do
		// elemento html
		resultado = paginaConversorTemperatura.obterResultadoTemperatura();

	}

	// Este teste só executa depois que o método
	// testeCalcularConversorTemperatura executar, pois este teste depende da
	// execução dele.
	@Test(dependsOnMethods = { "testeCalcularConversorTemperatura" })
	public void testeCelsiusParaCelsius() {

		// Esses dois primeiros métodos, selecinam a opção do elemento
		// html Select que tiver com x texto vísivel na aplicação web.
		selectDe.selectByVisibleText("Celsius [°C]");
		selectPara.selectByVisibleText("Celsius [°C]");

		// Verifica se são iguais, o resultado com o esperado (valor de entrada)
		Assert.assertEquals(resultado.getAttribute("value"),
				Double.parseDouble(entrada.getAttribute("value")));

	}

	// Este teste só executa depois que o método
	// testeCalcularConversorTemperatura executar, pois este teste depende da
	// execução dele.
	@Test(dependsOnMethods = { "testeCalcularConversorTemperatura" })
	public void testeCelsiusParaFahrenheit() {

		// Esses dois primeiros métodos, selecinam a opção do elemento
		// html Select que tiver com x texto vísivel na aplicação web.
		selectDe.selectByVisibleText("Celsius [°C]");
		selectPara.selectByVisibleText("Fahrenheit [°F]");

		// Atribui o valor do atribuito value do elemento html
		resultadoValor = resultado.getAttribute("value");

		// Atribui o resultado da conversão
		resultadoCalculado = ConversorTemperatura
				.celsiusParaFahrenheit(resultadoValor);

		// Verifica se são iguais, o resultado com o esperado
		Assert.assertEquals(resultadoValor, resultadoCalculado);

	}

	// Este teste só executa depois que o método
	// testeCalcularConversorTemperatura executar, pois este teste depende da
	// execução dele.
	@Test(dependsOnMethods = "testeCalcularConversorTemperatura")
	public void testeFahrenheitParaCelsius() {

		// Esses dois primeiros métodos, selecinam a opção do elemento
		// html Select que tiver com x texto vísivel na aplicação web.
		selectDe.selectByVisibleText("Fahrenheit [°F]");
		selectPara.selectByVisibleText("Celsius [°C]");

		// Atribui o valor do atribuito value do elemento html
		resultadoValor = resultado.getAttribute("value");

		// Atribui o resultado da conversão
		resultadoCalculado = ConversorTemperatura.fahrenheitParaCelsius(entrada
				.getAttribute("value"));

		// Verifica se são iguais, o resultado com o esperado
		Assert.assertEquals(resultadoValor, resultadoCalculado);

	}

	// Este teste só executa depois que o método
	// testeCalcularConversorTemperatura executar, pois este teste depende da
	// execução dele.
	@Test(dependsOnMethods = { "testeCalcularConversorTemperatura" })
	public void testeFahrenheitParafahrenheit() {

		// Esses dois primeiros métodos, selecinam a opção do elemento
		// html Select que tiver com x texto vísivel na aplicação web.
		selectDe.selectByVisibleText("Fahrenheit [°F]");
		selectPara.selectByVisibleText("Fahrenheit [°F]");

		// Verifica se são iguais, o resultado com o esperado (valor de entrada)
		Assert.assertEquals(resultado.getAttribute("value"),
				entrada.getAttribute("value"));

	}

}
