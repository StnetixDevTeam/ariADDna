package com.stnetix.ariaddna.desktopcore.configs;


import com.stnetix.ariaddna.commonutils.ui.interfaces.IUi;
import com.stnetix.ariaddna.desktopgui.guiApp.GuiApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.stnetix.ariaddna.commonutils")
public class CoreConfig{
    @Bean
    public IUi getUi() {
        return new GuiApp();
    }

}
