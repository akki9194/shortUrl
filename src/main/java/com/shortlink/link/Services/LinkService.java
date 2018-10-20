package com.shortlink.link.Services;

/**
 * <b>Created by Akash Hirke</b>
 * <b>Created by Akash Hirke</b>
 * <b>Link Service Class</b>
 */

import com.shortlink.link.Repo.LinkRepo;
import com.shortlink.link.modal.LinkModal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Service
public class LinkService {

    @Autowired
    private LinkRepo linkRepo;

    private String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char map[] = alpha.toCharArray();

    /**
     * <b>Creating tiny url</b>
     * @param num Unique number
     * @return tiny url
     */
    public String createShortLink (int num) {

        //num = Math.abs(num);
        int length = map.length;
        String s ="http://localhost:8080/short/";

        while (num > 0) {
            s = s + map[num%length];
            num = num / length;
        }
        return s;
    }

    /**
     * <b>Save url request data</b>
     * @param linkModal link modal object
     * @return link modal object
     */
    public LinkModal save(LinkModal linkModal) {
        return linkRepo.save(linkModal);
    }

    /**
     * decode th key to get original url
     * @param key short url key
     * @return link modal obkect
     */
    public LinkModal getLink (String key) {
        int id = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key);
        stringBuilder.reverse();
        key = stringBuilder.toString();
        char s[] = key.toCharArray();
        int len = map.length;
        for (int i=0; i<s.length; i++) {
            if (s[i] >= 'a' && s[i] <= 'z') {
                id = id * len + s[i] - 'a';
            }
            if (s[i] >= 'A' && s[i] <= 'Z') {
                id = id * len + s[i] - 'A' + 26;
            }
            if (s[i] >= '0' && s[i] <= '9') {
                id = id*len + s[i] - '0' + 52 ;
            }
        }
        LinkModal linkModal = linkRepo.getByLinkId(id);
        return linkModal;
    }

    /**
     * <b>Chec url is exits</b>
     * @param longUrl user url request
     * @return link modal object
     */
    public LinkModal getLinkModalByLongUrl(String longUrl) {
        return linkRepo.getByLongUrl(longUrl);
    }

    /**
     * <b>Request for all running tiny url</b>
     * @return list of link mddal
     */
    public String expireLinks() {
        List<LinkModal> linkModalList = linkRepo.getAllByExpireStatus("On");
        for (LinkModal linkModal : linkModalList) {
            long diff = (new Date().getTime() - linkModal.getCreateDate().getTime());
            long diffHours = diff / (60 * 60 * 1000) % 24;
            if (diffHours > 2) {
                linkModal.setExpireStatus("Off");
                linkRepo.save(linkModal);
            }
        }
        return "ok";
    }

}
