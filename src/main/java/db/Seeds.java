package db;

import db.helpers.*;
import models.*;

public class Seeds {

    public static void seedData(){

        DBArticle.deleteAll();
        DBWriter.deleteAll();
        DBCategory.deleteAll();
        DBTag.deleteAll();
        DBVisitor.deleteAll();

        Category category1 = new Category("Family");
        DBHelper.save(category1);

        Category category2 = new Category("Budget");
        DBHelper.save(category2);

        Category category3 = new Category("Cycling");
        DBHelper.save(category3);

        Category category4 = new Category("Foodie");
        DBHelper.save(category4);

        Category category5 = new Category("Walking");
        DBHelper.save(category5);

        Tag tag1 = new Tag("food");
        DBHelper.save(tag1);

        Tag tag2 = new Tag("city");
        DBHelper.save(tag2);

        Tag tag3 = new Tag("walking");
        DBHelper.save(tag3);

        Tag tag4 = new Tag("bike");
        DBHelper.save(tag4);

        Tag tag5 = new Tag("cheap");
        DBHelper.save(tag5);

        Visitor visitor = new Visitor("Stuart");
        DBHelper.save(visitor);

        Writer writer1 = new Writer("Aya Stafford", "Aya is a 27-year-old trainee doctor who enjoys going to the movies, reading and walking. She is kind and caring, but can also be very boring and a bit untidy.");
        DBHelper.save(writer1);

        Writer writer2 = new Writer("Hamid Cooke", "Hamid is a 30-year-old tradesperson whose life is dominated by cycling.");
        DBHelper.save(writer2);

        Writer writer3 = new Writer("Kaitlyn Markham", "My work explores the relationship between emerging trends and urban spaces."
                );
        DBHelper.save(writer3);

        Writer writer4 = new Writer("Jed Oakley", "With influences as diverse as Camus and Buckminster Fuller, new tensions are synthesised from both explicit and implicit meanings in my work.");
        DBHelper.save(writer4);

        Article article1 = new Article("The Forbidden City", writer1, "Due to the sheer scale of the Forbidden City, restoration is an ongoing endeavour, with ambitious plans to have 80% of the palace open to visitors by 2020 (in 2002, when the current restoration program began, only about 30% was accessible).", "Ringed by 3.5km of scarlet citadel walls at the very heart of Běijīng, the Unesco-listed Forbidden City is China’s largest and best-preserved collection of ancient buildings, and the largest palace complex in the world." );
        DBHelper.save(article1);

        Article article2 = new Article("Taj Mahal", writer2, "The Taj was built by Shah Jahan as a memorial for his third wife, Mumtaz Mahal, who died giving birth to their 14th child in 1631. The death of Mumtaz left the emperor so heartbroken that his hair is said to have turned grey virtually overnight. ", "Poet Rabindranath Tagore described it as 'a teardrop on the cheek of eternity'; Rudyard Kipling as 'the embodiment of all things pure'; while its creator, Emperor Shah Jahan, said it made 'the sun and the moon shed tears from their eyes'." );
        DBHelper.save(article2);

        Article article3 = new Article("Topkapi Palace", writer3, "Mehmet the Conqueror built the first stage of the palace shortly after the Conquest in 1453, and lived here until his death in 1481. Subsequent sultans lived in this rarefied environment until the 19th century.", "Topkapı is the subject of more colourful stories than most of the world's museums put together. A visit to the palace's opulent pavilions, jewel-filled Treasury and sprawling Harem gives a fascinating glimpse into their lives." );
        DBHelper.save(article3);

        Article article4 = new Article("The Grand Canyon", writer4, "It’s difficult, perhaps impossible, to get your head round the scale of the 277 miles of rock, water, plants and animals that make up the length of the Grand Canyon. Most people’s first glimpse is from Grand Canyon Village on the South Rim.", "Humbling geological features, fascinating Native American history, forest-covered mountains, and a vast range of activities – its nickname is The Grand Canyon State but Arizona has much more to offer the visitor than that undeniably grand canyon." );
        DBHelper.save(article4);

        Article article5 = new Article("Hol Chan Marine Reserve", writer1, "Although the reef is the primary attraction of Hol Chan, the marine reserve also includes sea-grass beds and mangroves. The sea grass provides a habitat for nurse sharks and southern stingrays, which lend their name to Shark Ray Alley.", "At the southern tip of Ambergris, the Hol Chan Marine Reserve is probably Belize's most oft-visited diving site due to its spectacular coral formations." );
        DBHelper.save(article5);

        Article article6 = new Article("Fusterlandia", writer2, "Fusterlandia, an ongoing project first hatched around 20 years ago that has covered several suburban blocks with whimsical but highly stylized public art. A fantastical mishmash of spiraling walkways, rippling pools and sunburst fountains. ", "Cuban artist José Fuster has turned his home neighborhood into a masterpiece of intricate tilework and kaleidoscopic colors – a street-art extravaganza that makes Barcelona’s Park Güell look positively sedate." );
        DBHelper.save(article6);


        article1.addTag(tag1);
        article1.addTag(tag3);
        article1.addCategory(category5);

        article2.addTag(tag5);
        article2.addTag(tag4);
        article2.addCategory(category4);

        article3.addTag(tag2);
        article3.addTag(tag4);
        article3.addCategory(category3);

        article4.addTag(tag1);
        article4.addTag(tag3);
        article4.addCategory(category2);

        article5.addTag(tag2);
        article5.addTag(tag4);
        article5.addCategory(category1);

        article6.addTag(tag2);
        article6.addTag(tag3);
        article6.addCategory(category4);

        DBHelper.update(article1);
        DBHelper.update(article2);
        DBHelper.update(article3);
        DBHelper.update(article4);
        DBHelper.update(article5);
        DBHelper.update(article6);


    }


}
