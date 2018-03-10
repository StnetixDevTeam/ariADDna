/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.desktopgui.views;

import javafx.fxml.FXMLLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * FXML loader provider
 * create custom FXML loader from Spring context
 *
 * @author slonikmak
 */
@Component
public class FXMLLoaderProvider {
    private AnnotationConfigApplicationContext ctx;

    /**
     * Inject Spring context
     * @param ctx Spring context
     */
    @Autowired
    public void setCtx(AnnotationConfigApplicationContext ctx) {
        this.ctx = ctx;
    }

    public FXMLLoader get(String path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.setControllerFactory(param -> ctx.getBean(param));
        return loader;
    }
}
