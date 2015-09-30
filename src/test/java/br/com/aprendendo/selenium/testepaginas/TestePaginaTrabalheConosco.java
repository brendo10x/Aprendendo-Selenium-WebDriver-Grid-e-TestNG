package br.com.aprendendo.selenium.testepaginas;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.aprendendo.seleniumwebdriveregrid.config.TipoDriver;
import br.com.aprendendo.seleniumwebdriveregrid.fabricaobjetopagina.PaginaTrabalheConosco;

//Esta classe tem como objetivo executar os testes funcionais na página trabalhe conosco
//(http://artigo.pe.hu/?pg=trabalhe_conosco.php) da aplicação web   

public class TestePaginaTrabalheConosco {
	// Declaração de variável
	WebDriver driver;

	// Primeiro método a ser chamado na classe, pois está anotado com
	// @BeforeClass do TestNG
	@BeforeClass
	public void inicializarDriver() throws Exception {

		// Atribuição da instância do Enum do Tipo Firefox
		TipoDriver selectedDriverType = TipoDriver.FIREFOX;

		// Atribuição das capacidades para usar o tipo driver(Firefox)
		DesiredCapabilities desiredCapabilities = selectedDriverType
				.obterDesiredCapabilities();

		// Atribuição do WebDriver remoto, com as definições de que os testes
		// funcionais desta classe serão executados no navegador Google Chrome
		// do Node http://192.168.0.9:5555/wd/hub (1º máquina virtual usando
		// Linux)
		driver = selectedDriverType.obterObjetoWebDriverRemoto(
				desiredCapabilities, Platform.LINUX,
				"http://192.168.0.9:5555/wd/hub");

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
	// tesng.xml (TestNG) declarado neste projeto
	@Parameters({ "nomeParametro" })
	// Executa o método como teste
	@Test
	public void testeEnviarMensagemDeTrabalheConosco(String nomeParametro) {

		// Este teste executa como se fosse passos de usuário
		// Inicia a página trabalhe conosco
		PaginaTrabalheConosco paginaTrabalheConosco = new PaginaTrabalheConosco(
				driver);
		// Estando na página Conversor de temperatura
		// Preenche o campo nome
		paginaTrabalheConosco.preencherNome(nomeParametro);
		// Preenche o campo email
		paginaTrabalheConosco.preencherEmail("brendo10x@gmail.com");
		// Marca a opção do radio button
		paginaTrabalheConosco.marcarRadioButtonSexoMasculino();
		// Marca a opção do checkbox
		paginaTrabalheConosco.marcarCkeckBoxIdiomaIngles();
		// Seleciona a opção do select
		paginaTrabalheConosco.selecionarVaga("Suporte de TI");
		// Escolhe o arquivo pdf
		paginaTrabalheConosco
				.selecionarArquivoCurriculo("/home/brendo/curriculo.pdf");
		// Clica no botão para enviar o formulário com as informações acima
		paginaTrabalheConosco.enviarMensagemDeTrabalheConosco();
		// Retorna a mensagem informada na página conversor temperatura
		String msg = paginaTrabalheConosco.obterMensagemAposEnvio();
		// Verifica se são iguais, o resultado com o esperado
		Assert.assertEquals(msg, "Sucesso!");
	}
}
