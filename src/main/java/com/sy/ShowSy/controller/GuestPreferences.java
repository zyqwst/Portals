/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sy.ShowSy.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class GuestPreferences implements Serializable {
    
    /** serialVersionUID*/
	private static final long serialVersionUID = 6185003113087195566L;

	private Map<String,String> themeColors;
    
    private String theme = "blue";

    private String layout = "blue";
            
    private boolean overlayMenu;
    
    private boolean darkMenu;
    
    private boolean orientationRTL;
        
    @PostConstruct
    public void init() {
        themeColors = new HashMap<String,String>();
        themeColors.put("turquoise", "#47c5d4");
        themeColors.put("blue", "#3192e1");
        themeColors.put("orange", "#ff9c59");
        themeColors.put("purple", "#985edb");
        themeColors.put("pink", "#e42a7b");
        themeColors.put("purple", "#985edb");
        themeColors.put("green", "#5ea980");
        themeColors.put("black", "#545b61"); 
    }
    
    
	public String getTheme() {		
		return theme;
	}
    
	public void setTheme(String theme) {
		this.theme = theme;
        this.layout = theme;
	}

    public String getLayout() {		
		return layout;
	}
        
    public boolean isDarkMenu() {
        return this.darkMenu;
    }
    
    public void setDarkMenu(boolean value) {
        this.darkMenu = value;
    }
    
    public boolean isOverlayMenu() {
        return this.overlayMenu;
    }
    
    public void setOverlayMenu(boolean value) {
        this.overlayMenu = value;
    }
    
    public Map getThemeColors() {
        return this.themeColors;
    }

    public boolean isOrientationRTL() {
        return orientationRTL;
    }

    public void setOrientationRTL(boolean orientationRTL) {
        this.orientationRTL = orientationRTL;
    }
}
