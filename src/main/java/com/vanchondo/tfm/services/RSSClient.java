package com.vanchondo.tfm.services;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.vanchondo.tfm.dtos.RssItem;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RSSClient {

    private static String url = "https://www.criptonoticias.com/feed/";
    public List<RssItem> getRssItems() throws IOException {
        RssReader reader = new RssReader();
        return reader.read(url).map(item -> {
            RssItem rssItem = new RssItem();
            rssItem.setAuthor(item.getAuthor().orElse(null));
            rssItem.setCategory(item.getCategory().orElse(null));
            rssItem.setChannel(item.getChannel());
            rssItem.setEnclosure(item.getEnclosure().orElse(null));
            rssItem.setGuid(item.getGuid().orElse(null));
            rssItem.setIsPermaLink(item.getIsPermaLink().orElse(null));
            rssItem.setLink(item.getLink().orElse(null));
            rssItem.setPubDate(item.getPubDate().orElse(null));
            rssItem.setTitle(cleanStringFromHTML(item.getTitle().orElse(null)));
            String description = item.getDescription().orElse(null);
            rssItem.setImgUrl(extractImgUrl(description));
            description = cleanStringFromHTML(description);
            description = description.split("\\n")[0];
            rssItem.setDescription(description);

            return rssItem;
        }).collect(Collectors.toList());
    }

    private String extractImgUrl(String text){
        String url = null;
        Pattern p = Pattern.compile("img .* src=\"(.*?)\"");
        Matcher m = p.matcher(text);
        if (m.find()) {
            url = m.group(1);
        }

        return url;
    }

    private String cleanStringFromHTML(String text){
        PolicyFactory policy = Sanitizers.FORMATTING;
        String safeHTML = policy.sanitize(text);

        return safeHTML;
    }
}
