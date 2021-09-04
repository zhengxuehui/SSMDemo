package com.itcast.pachong;


import com.itcast.pachong.service.ApiService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class APIServiceTest {

    @Autowired
    private ApiService apiService;


    @Test
    public void testGetHtml() {
        String html = this.apiService.getHtml("https://www.autohome.com.cn/3362/#pvareaid=6850206");

        Document dom = Jsoup.parse(html);

        System.out.println(dom.select("title").first().text());

    }


    @Test
    public void testGetImage() {
        String image = this.apiService.getImage("https://car2.autoimg.cn/cardfs/product/g24/M09/B2/A6/1024x0_1_q95_autohomecar__ChsEl1-foNuARhNCABnR6k7agAg910.jpg");

        System.out.println(image);
    }

}
