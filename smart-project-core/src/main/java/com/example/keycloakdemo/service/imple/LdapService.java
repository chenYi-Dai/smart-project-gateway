package com.example.keycloakdemo.service.imple;

import com.example.keycloakdemo.dao.PersonAttributeMapper;
import com.example.keycloakdemo.dao.model.CustomerInfo;
import com.example.keycloakdemo.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class LdapService {

    private final static Logger log = LoggerFactory.getLogger(LdapService.class);

    @Resource
    private LdapTemplate ldapTemplate;

    public String test(){
        log.info("test");
        return "test";
    }

    /**
     * 添加 一条记录
     * @param person
     */
    public void createOnePerson(User person) throws NamingException {

        ContextSource contextSource = ldapTemplate.getContextSource();

        LdapContext ctx = (LdapContext) contextSource.getReadWriteContext();

        BasicAttributes attrsbu = new BasicAttributes();
        BasicAttribute objclassSet = new BasicAttribute("objectClass");
        objclassSet.add("posixAccount");
        objclassSet.add("inetOrgPerson");
        objclassSet.add("top");
        objclassSet.add("shadowAccount");
        attrsbu.put(objclassSet);
        attrsbu.put("uid",  person.getUserName());//显示账号
        attrsbu.put("sn", person.getFirstName());//显示姓名
        attrsbu.put("cn", person.getUserName());//显示账号
        //attrsbu.put("gecos", lu.getCn());//显示账号
        attrsbu.put("userPassword", person.getPassword());//显示密码
        //attrsbu.put("displayName", person.getDisplayName());//显示描述
        attrsbu.put("mail", person.getEmail());//显示邮箱
        attrsbu.put("homeDirectory", "/");//显示home地址
        attrsbu.put("loginShell", "/bin/bash");//显示shell方式
        attrsbu.put("uidNumber", createRandonNumber()+"");/*显示id */
        attrsbu.put("gidNumber", createRandonNumber()+"");/*显示组id */

        try {
            String dn="uid="+person.getUserName()+",ou=User,dc=maxcrc,dc=com";
            System.out.println(dn);
            ctx.createSubcontext(dn, attrsbu);
            System.out.println("添加用户成功");
        } catch (Exception e) {
            System.out.println("添加用户失败");
            e.printStackTrace();
        }


        ctx.close();





       /* BasicAttribute ba = new BasicAttribute("objectclass");
        ba.add("person"); //此处的person对应的是core.schema文件中的objectClass：person
        Attributes attr = new BasicAttributes();
        attr.put(ba);
        //必填属性，不能为null也不能为空字符串
        attr.put("cn", person.getUserName());
        attr.put("sn", person.getFirstName());

        //可选字段需要判断是否为空，如果为空则不能添加


        if (person.getPassword() != null
                && person.getPassword().length() > 0) {
            attr.put("userPassword", person.getPassword());
        }
*//*
        if (person.getEmail() != null
                && person.getEmail().length() > 0) {
            attr.put("email", person.getEmail());
        }*//*

        //bind方法即是添加一条记录。
        ldapTemplate.bind(getDn(person.getUserName()), null, attr);*/
    }
    protected Long createRandonNumber(){
        long randomNumber = (long)(Math.random()*100+1);
        return randomNumber;
    }


    /**

     /**
     * 根据dn查询详细信息
     * @param cn
     * @return
     */
    public User getPersonDetail(String cn) {
        try {
            //ldapTeplate的lookup方法是根据dn进行查询，此查询的效率超高
            User ua = (User)
                    ldapTemplate.lookup(getDn(cn),
                            new PersonAttributeMapper());
            return ua;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /**
     * 根据自定义的属性值查询person列表
     * @param person
     * @return
     */
    public List<User> getPersonList(
            User person) {
        List<User> list = new ArrayList<User>();
        //查询过滤条件
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass", "person"));


        if (person.getUserName() != null
                && person.getUserName().length() > 0) {
            andFilter.and(new EqualsFilter("cn", person.getUserName()));
        }
        if (person.getFirstName() != null
                && person.getFirstName().length() > 0) {
            andFilter.and(new EqualsFilter("sn", person.getFirstName()));
        }

        if (person.getPassword() != null
                && person.getPassword().length() > 0) {
            andFilter.and(new EqualsFilter("userPassword", person.getPassword()));
        }

        if (person.getEmail() != null
                && person.getEmail().length() > 0) {
            andFilter.and(new EqualsFilter("email", person.getEmail()));
        }
        //search是根据过滤条件进行查询，第一个参数是父节点的dn，可以为空，不为空时查询效率更高
        list = ldapTemplate.search("", andFilter.encode(),
                new PersonAttributeMapper());
        return list;
    }

    /**
     * 删除一条记录，根据dn
     * @param cn
     */
    public void removeOnePerson(String cn) {
        ldapTemplate.unbind(getDn(cn));
    }

    /**
     * 修改操作
     * @param person
     */
    public void updateOnePerson(User person) {
        if (person == null || person.getUserName() == null
                || person.getUserName().length() <= 0) {
            return;
        }
        List<ModificationItem> mList = new ArrayList<ModificationItem>();

        mList.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("sn",person.getFirstName())));


        mList.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("email",person.getEmail())));
        mList.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                new BasicAttribute("userPassword",person.getPassword())));

        if (mList.size() > 0) {
            ModificationItem[] mArray = new ModificationItem[mList.size()];
            for (int i = 0; i < mList.size(); i++) {
                mArray[i] = mList.get(i);
            }
            //modifyAttributes 方法是修改对象的操作，与rebind（）方法需要区别开
            ldapTemplate.modifyAttributes(this.getDn(person.getUserName()), mArray);
        }
    }
    /**
     * 得到dn
     * @param cn
     * @return
     */
    private DistinguishedName getDn(String cn) {
        //得到根目录，也就是配置文件中配置的ldap的根目录
        DistinguishedName newContactDN = new DistinguishedName();
        // 添加cn，即使得该条记录的dn为"cn=cn,根目录",例如"cn=abc,dc=testdc,dc=com"
        newContactDN.add("cn", cn);
        return newContactDN;
    }

    public CustomerInfo test(String cn) {
        CustomerInfo sakudh = CustomerInfo.builder().mobile("sakudh").build();
        return sakudh;
    }
}
