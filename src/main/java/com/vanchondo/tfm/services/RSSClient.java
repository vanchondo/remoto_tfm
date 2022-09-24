package com.vanchondo.tfm.services;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RSSClient {

    private static String url = "https://www.criptonoticias.com/feed/";
    public List<Item> getRssItems() throws IOException {
        RssReader reader = new RssReader();
        return reader.read(url).collect(Collectors.toList());
    }
}
