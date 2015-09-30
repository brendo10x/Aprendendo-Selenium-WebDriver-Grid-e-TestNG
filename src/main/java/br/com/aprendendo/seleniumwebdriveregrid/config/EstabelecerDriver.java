package br.com.aprendendo.seleniumwebdriveregrid.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/*Fonte: https://www.safaribooksonline.com/library/view/mastering-selenium-webdriver/9781784394356/ch01s06.html*/
public interface EstabelecerDriver {

	WebDriver obterObjetoWebDriver(DesiredCapabilities desiredCapabilities);

	WebDriver obterObjetoWebDriverRemoto(
			DesiredCapabilities desiredCapabilities, Platform plataforma,
			String enderecoRemoto);

	DesiredCapabilities obterDesiredCapabilities();
}
