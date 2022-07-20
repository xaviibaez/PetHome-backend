package com.pethome.pethome.Utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pethome.pethome.security.services.UserDetailsImpl;

public class Utils {    
    public static Long userLogged(){
        
        Object userLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userLogged instanceof UserDetailsImpl) {
			return ((UserDetailsImpl)userLogged).getId();
		}
        
        return null;
    }
}
