package com.ketee_jishs.moviesapplication.main

import android.os.Handler
import com.ketee_jishs.moviesapplication.adapter.ItemFilm

@Suppress("DEPRECATION")
class RepoMainModel() {
    var dataSource: ArrayList<ItemFilm> = ArrayList(10)

    fun getFilms(onFilmReadyCallback: OnFilmReadyCallback) {
        val name = ArrayList<String> (listOf("Бойцовский клуб", "Карты, деньги, два ствола", "Апокалипсис сегодня",
                "Звёздные войны: Эпизод 4 - Новая надежда", "Звёздный путь: Фильм",
                "Гарри Поттер и философский камень", "Железный человек", "Капитан Америка: Первый мститель",
                "Тёмный рыцарь", "В поисках Немо"))

        val id = ArrayList<String> (listOf("550", "100", "28", "11", "152",
                                           "671", "1726", "1771", "155", "12"))

        val year = ArrayList<String> (listOf("1999", "1998", "1979", "1977", "1979",
                                             "2001", "2008", "2011", "2008", "2003"))

        val rating = ArrayList<String> (listOf("8.4", "8.2", "8.3", "8.2", "6.4",
                                               "7.9", "7.6", "6.9", "8.5", "7.8"))

        for (i in name.indices) {
            dataSource.add(ItemFilm(name[i], id[i], year[i], rating[i]))
        }

        Handler().post{onFilmReadyCallback.onDataReady(dataSource)}
    }
}

interface OnFilmReadyCallback {
    fun onDataReady(data : ArrayList<ItemFilm>)
}