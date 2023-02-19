package com.example.keycloakdemo.dao;

import com.example.keycloakdemo.vo.User;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.directory.Attributes;

public class PersonAttributeMapper implements AttributesMapper {
    @Override
    public Object mapFromAttributes(Attributes attr) throws NamingException, javax.naming.NamingException {
                 User person = new User();
                 person.setFirstName((String)attr.get("sn").get());
                 person.setUserName((String)attr.get("cn").get());

                 if (attr.get("userPassword") != null) {
                     person.setPassword((String)attr.get("userPassword").get());
                     }
                if (attr.get("email") != null) {
                         person.setEmail((String)attr.get("email").get());
                 }

                 return person;
             }
}
