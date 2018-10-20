package com.shortlink.link.Controller;

/**
 * <b>Created by Akash Hirke</b>
 * This api get the long url and return the short(tiny) url.
 */

import com.shortlink.link.Services.LinkService;
import com.shortlink.link.modal.LinkModal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/short")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * <b>Get long url from user.</b>
     * @param longUrl get url form user
     * @param response status code
     * @return tiny url
     */
    @RequestMapping(method = RequestMethod.POST, path = "/link")
    public @ResponseBody String shortUrl (@RequestParam("longUrl") String longUrl,
                                          HttpServletResponse response,
                                          HttpServletRequest request) {

        try {
            LinkModal linkModal = linkService.getLinkModalByLongUrl(longUrl);
            if (linkModal != null) {
                if (linkModal.getExpireStatus().equalsIgnoreCase("On")) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    return linkModal.getShortUrl();
                }
                else {
                    linkModal.setExpireStatus("On");
                    linkModal.setCreateDate(new Date());
                    linkModal = linkService.save(linkModal);
                    response.setStatus(HttpServletResponse.SC_OK);
                    return linkModal.getShortUrl();
                }
            }
            else {
                int num = Math.abs(longUrl.hashCode());
                linkModal = new LinkModal();
                linkModal.setLinkId(num);
                linkModal.setLongUrl(longUrl);
                linkModal.setCreateDate(new Date());
                linkModal.setExpireStatus("On");
                linkModal.setShortUrl(linkService.createShortLink(num));
                linkModal = linkService.save(linkModal);
                response.setStatus(HttpServletResponse.SC_OK);
                return linkModal.getShortUrl();
            }
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return "Error";
        }
    }

    /**
     * <b>Redirect to main url.</b>
     * @param key read key from short url.
     * @param response status code
     * @return redirect to main url
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{key}")
    public @ResponseBody String get (@PathVariable String key, HttpServletResponse response,
                                     HttpServletRequest request) {

        try {
            log.info("Key :: ## ::"+key);
            LinkModal linkModal = linkService.getLink(key);

            String url = linkModal.getLongUrl();
            response.sendRedirect(url);
            return "redirect";
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            e.printStackTrace();
            return "Error";
        }
    }
}
