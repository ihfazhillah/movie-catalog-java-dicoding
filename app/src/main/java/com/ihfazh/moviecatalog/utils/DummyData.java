package com.ihfazh.moviecatalog.utils;


import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static List<MovieEntity> generateMovies(){

        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                        "Soul (2020)",
                        "1h 42m",
                        "Released",
                        "English",
                        "$150,000,000.00",
                        "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                        "84%"
                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/mMWLGu9pFymqipN8yvISHsAaj72.jpg",
                        "Dory's Reef Cam (2020)",
                        "3h 1m",
                        "Released",
                        "English",
                        "-",
                        "Dive into the waters below and watch the aquatic wildlife from the workdl of Nemo and Dory.",
                        "66%"
                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/zGVbrulkupqpbwgiNedkJPyQum4.jpg",
                        "My Hero Academia: Heroes Rising",
                        "1h 44m",
                        "Released",
                        "Japanese",
                        "-",
                        "Class 1-A visits Nabu Island where they finally get to do some real hero work. The place is so peaceful that it's more like a vacation … until they're attacked by a villain with an unfathomable Quirk! His power is eerily familiar, and it looks like Shigaraki had a hand in the plan. But with All Might retired and citizens' lives on the line, there's no time for questions. Deku and his friends are the next generation of heroes, and they're the island's only hope.",
                        "84%"
                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
                        "The SpongeBob Movie: Sponge on the Run (2020)",
                        "1h 35m",
                        "Released",
                        "English",
                        "$60,000,000.00",
                        "When his best firend Garry is suddenly snatched away, SpongeBob takse Patrick on a madcap mission for beyond Bikini Bottom to save their pink shelled pal.",
                        "79%"
                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/iB64vpL3dIObOtMZgX3RqdVdQDc.jpg",
                        "Sherk (2001)",
                        "1h 30m",
                        "Released",
                        "English",
                        "$60,000,000.00",
                        "It ain't easy bein' green -- especially if you're a likable (albeit smelly) ogre named Shrek. On a mission to retrieve a gorgeous princess from the clutches of a fire-breathing dragon, Shrek teams up with an unlikely compatriot -- a wisecracking donkey.\n",
                        "76%"

                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/oljiDFPyMY437BRRV69AzVDSiKy.jpg",
                        "Sherk 2 (2004)",
                        "1h 33m",
                        "Released",
                        "English",
                        "$150,000,000.00",
                        "Shrek, Fiona and Donkey set off to Far, Far Away to meet Fiona's mother and father. But not everyone is happy. Shrek and the King find it hard to get along, and there's tension in the marriage. The fairy godmother discovers that Shrek has married Fiona instead of her Son Prince Charming and sets about destroying their marriage.",
                        "71%"
                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/tLOKZvVaewxmjbP97vibaN2hs6i.jpg",
                        "Albi Lumiukko (2017)",
                        "-",
                        "Released",
                        "Finnsih",
                        "-",
                        "-",
                        "100%"

                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/whAZnIg0THcMv9LvPd60a5ORE3n.jpg",
                        "Happy Machine",
                        "16m",
                        "Released",
                        "Japanese",
                        "-",
                        "An infant suddenly discovers that everything in its nursery is fake and goes out into the world outside where it encounters a range of alien like creatures, some friendly and some not.",
                        "100%"
                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/3iy8XN5nlCewKP9YGlNEuHOo8dq.jpg",
                        "Pororo 5: Treasure Island Adventure",
                        "1h 19m",
                        "Released",
                        "Korean",
                        "-",
                        "Pororo and his friends at the pirate restaurant accidentally take a treasure map of the legendary treasure and head to the treasure island. While searching for friends scattered on the mysterious treasure island where the secrets of ancient civilization are kept, they meet a long-trapped pirate hero, Captain Silver, and solves the mystery of the treasure map with him.\n\n",
                        "90%"
                )
        );

        movies.add(
                new MovieEntity(
                        "https://www.themoviedb.org/t/p/original/kzKJxfIdZ70nsPfKyq7hlYlJwSx.jpg",
                        "The Boy and Thea Beast (2015)",
                        "1h 59m",
                        "Released",
                        "Japanese",
                        "-",
                        "Kyuta, a boy living in Shibuya, and Kumatetsu, a lonesome beast from Jutengai, an imaginary world. One day, Kyuta forays into the imaginary world and, as he's looking for his way back, meets Kumatetsu who becomes his spirit guide. That encounter leads them to many adventures.",
                        "80%"
                )
        );

        return movies;
    }

    public static List<TvShowEntity> generateTvShows(){

        ArrayList<TvShowEntity> tvShows = new ArrayList<>();
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                        "The Simpsons (1989)",
                        "Returning Series",
                        "Scripted",
                        "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.\n",
                        "78%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/8kOWDBK6XlPUzckuHDo3wwVRFwt.jpg",
                        "Rick and Morty",
                        "Returning Series",
                        "Scripted",
                        "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                        "87%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/4olv2DdNUdg7oE6zoevbZbgEPsB.jpg",
                        "Futurama",
                        "Ended",
                        "Scripted",
                        "The adventures of a late-20th-century New York City pizza delivery boy, Philip J. Fry, who, after being unwittingly cryogenically frozen for one thousand years, finds employment at Planet Express, an interplanetary delivery company in the retro-futuristic 31st century.\n\n",
                        "83%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/7v1eODxlqBuOJk5HTtTWURRd1Ke.jpg",
                        "Archer (2009)",
                        "Returning Series",
                        "Scripted",
                        "Sterling Archer is the world's most daunting spy. He works for ISIS, a spy agency run by his mother. In between dealing with his boss and his co-workers - one of whom is his ex-girlfriend - Archer manages to annoy or seduce everyone that crosses his path. His antics are only excusable because at the end of the day, he still somehow always manages to thwart whatever crises was threatening mankind.\n\n",
                        "79%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/dBsDWUcdfbuZwglgyeeQ9ChRoS4.jpg",
                        "Dragon Ball Z (1989)",
                        "Ended",
                        "Scripted",
                        "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.\n\n",
                        "81%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/7Qspx2eFX0uBSQLLlAKnYrjZgse.jpg",
                        "Digimon Adventure: (2020)",
                        "Returning Series",
                        "Scripted",
                        "The new anime will take place in 2020 and will feature an all-new story centering on Taichi Yagami when he is in his fifth year in elementary school. His partner is Agumon. The story begins in Tokyo when a large-scale network malfunction occurs. Taichi is preparing for his weekend summer camping trip when the incident happens. Taichi's mother and his younger sister Hikari get stuck on a train that won't stop moving, and Taichi heads to Shibuya in order to help them. However, on his way there, he encounters a strange phenomenon and sweeps him up into the Digital World along with the other DigiDestined.",
                        "74%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/lU1ollblNqLSAof7fzvfiHSqb9C.jpg",
                        "Ben 10",
                        "Ended",
                        "Scripted",
                        "When 10-year-old Ben Tennyson discovers a mysterious device, he gains the power to change into ten different alien heroes, each with uniquely awesome powers. With such abilities at his disposal, Ben realizes a greater responsibility to help others and stop evildoers, but that doesn't mean he's above a little superpowered mischief now and then.\n\n",
                        "80%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/iE3s0lG5QVdEHOEZnoAxjmMtvne.jpg",
                        "One-Punch Man",
                        "Returning Series",
                        "Scripted",
                        "Saitama is a hero who only became a hero for fun. After three years of “special” training, though, he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch, and it turns out that being devastatingly powerful is actually kind of a bore. With his passion for being a hero lost along with his hair, yet still faced with new enemies every day, how much longer can he keep it going?\n\n",
                        "84%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/zHgc9nTXiP77qoy14BO7WUFTwkp.jpg",
                        "Captain Tsubasa",
                        "Ended",
                        "Scripted",
                        "The passionate story of an elementary school student whose thoughts and dreams revolve almost entirely around the love of soccer. 11-year-old Tsubasa Oozora started playing football at a very young age, and while it was mostly just a recreational sport for his friends, for him, it developed into something of an obsession. In order to pursue his dream to the best of his elementary school abilities, Tsubasa moves with his mother to Nankatsu city, which is well-known for its excellent elementary school soccer teams. But although he was easily the best in his old town, Nankatsu has a lot more competition, and he will need all of his skill and talent in order to stand out from this new crowd.\n" +
                                "\n" +
                                "He encounters not only rivals, but also new friends like the pretty girl Sanae Nakazawa and the talented goalkeeper, Genzo Wakabayashi, who shares the same passion as Tsubasa, and will prove to be a treasured friend in helping him push towards his dreams. Representing Japan in the FIFA World Cup is Tsubasa’s ultimate dream, but it will take a lot more than talent to reach it.\n" +
                                "\n",
                        "79%"
                )
        );
        tvShows.add(
                new TvShowEntity(
                        "https://www.themoviedb.org/t/p/original/1aklTBd36LFiFNroOSiherLvLdu.jpg",
                        "American Dad! (2005)",
                        "Returning Series",
                        "Scripted",
                        "The series focuses on an eccentric motley crew that is the Smith family and their three housemates: Father, husband, and breadwinner Stan Smith; his better half housewife, Francine Smith; their college-aged daughter, Hayley Smith; and their high-school-aged son, Steve Smith. Outside of the Smith family, there are three additional main characters, including Hayley's boyfriend turned husband, Jeff Fischer; the family's man-in-a-goldfish-body pet, Klaus; and most notably the family's zany alien, Roger, who is \"full of masquerades, brazenness, and shocking antics.\"\n" +
                                "\n",
                        "66%"
                )
        );
        return tvShows;
    }
}
