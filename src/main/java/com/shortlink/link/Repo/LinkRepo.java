package com.shortlink.link.Repo;

/**
 * <b>Created by Akash Hirke</b>
 * <b>Link Repo Class</b>
 * <b> connect with modal class and repository</b>
 * <b>get and store data in database</b>
 */

import com.shortlink.link.modal.LinkModal;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LinkRepo extends Repository<LinkModal, String> {

    /**
     * <b>save data in url table.</b>
     * @param linkModal modal class object
     * @return url data from DB
     */

    LinkModal save(LinkModal linkModal);

    /**
     * <b>Request data by Id.</b>
     * @param linkId table Id
     * @return link modal object
     */

    LinkModal getByLinkId(int linkId);

    /**
     * <b>Request data by url</b>
     * @param url user request url
     * @return link modal object
     */
    LinkModal getByLongUrl(String url);

    /**
     * <b>Request for all active tiny url</b>
     * @param expire status to check on or off
     * @return all active tiny url
     */

    List<LinkModal> getAllByExpireStatus(String expire);

}
