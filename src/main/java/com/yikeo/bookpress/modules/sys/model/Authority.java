package com.yikeo.bookpress.modules.sys.model;

import com.yikeo.bookpress.common.model.BaseModel;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author WuJing
 * @since 2016-11-23
 */

@Entity
@Table(name = "SYS_AUTHORITY")
public class Authority extends BaseModel implements GrantedAuthority {

    private String authority;

    private String description;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
