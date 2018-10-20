package com.shortlink.link.modal;

/**
 * <b>URl Modal Class</b>
 * <b>Implement getter and setter.</b>
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "url")
public class LinkModal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "link_id")
    private int linkId;
    @Column(name = "long_url")
    private String longUrl;
    @Column(name = "short_url")
    private String shortUrl;
    @Column(name = "expire_status")
    private String expireStatus;
    @Column(name = "create_date")
    private Date createDate;

}
