package br.com.aprendendo.seleniumwebdriveregrid.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//Este enum tem como objetivo fornecer tipos de enums que tem implementado métodos úteis
//para a chamada de instâncias de WebDriver's (Firefox e Chrome)

public enum TipoDriver implements EstabelecerDriver {

	FIREFOX {

		// Este método retorna as capacidades para usar o Firefox
		public DesiredCapabilities obterCapacidadesDesejadas() {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			return capabilities;
		}

		// Este método retorna um instância do Driver do Firefox para uso
		public WebDriver obterObjetoWebDriver(DesiredCapabilities capabilities) {
			return new FirefoxDriver(capabilities);
		}

		// Este método retorna um objeto remoto
		public WebDriver obterObjetoWebDriverRemoto(
				DesiredCapabilities capabilities, Platform plataforma,
				String enderecoRemoto) {

			// Define o sistema operacional
			capabilities.setPlatform(plataforma);

			WebDriver driver = null;
			try {
				// Atribuição da instância do objeto remoto WebDriver
				driver = new RemoteWebDriver(new URL(enderecoRemoto),
						capabilities);

			} catch (MalformedURLException e) {

				e.printStackTrace();
			}

			// retorna instância
			return driver;
		}

	},
	CHROME {

		// // Este método retorna as capacidades para usar o Chrome
		public DesiredCapabilities obterCapacidadesDesejadas() {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();

			// Inicio: Neste bloco de código, estamos definindo alguns comandos
			// para o Google Chrome, pois nós não queremos que nos fique
			// perguntando se ele pode
			// ser o navegador padrão em cada vez que é iniciado, portanto,
			// desativamos essa verificação. Também desativamos o
			// gerenciador de senhas para que ele não pergunta se você gostaria
			// de salvar seus dados de login cada vez que você tem um teste que
			// executa uma ação de login.
			capabilities.setCapability("chrome.switches",
					Arrays.asList("--no-default-browser-check"));

			HashMap<String, String> chromePreferences = new HashMap<String, String>();

			chromePreferences.put("profile.password_manager_enabled", "false");

			capabilities.setCapability("chrome.prefs", chromePreferences);
			// Fim

			return capabilities;
		}

		// Este método retorna um instância do Driver do Chrome para uso
		public WebDriver obterObjetoWebDriver(DesiredCapabilities capabilities) {
			return new ChromeDriver(capabilities);
		}

		// Este método retorna um objeto WebDriver remoto
		public WebDriver obterObjetoWebDriverRemoto(
				DesiredCapabilities capabilities, Platform plataforma,
				String enderecoRemoto) {

			// Define o sistema operacional
			capabilities.setPlatform(plataforma);

			WebDriver driver = null;
			try {
				// Atribuição da instância do objeto remoto WebDriver
				driver = new RemoteWebDriver(new URL(enderecoRemoto),
						capabilities);
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}

			return driver;
		}

	}
}