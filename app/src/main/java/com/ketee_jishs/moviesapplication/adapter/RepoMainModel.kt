package com.ketee_jishs.moviesapplication.adapter

import android.os.Handler

@Suppress("DEPRECATION")
class RepoMainModel() {
    var fantasticFilms: ArrayList<ItemFilm> = ArrayList(10)
    var horrorFilms: ArrayList<ItemFilm> = ArrayList(10)

    fun getFantasticFilms(onFilmReadyCallback: OnFilmReadyCallback) {
        val name = ArrayList<String> (listOf("Чужой", "Звёздные войны: Эпизод 3 - Месть Ситхов",
                                             "Звёздный путь: Фильм", "Апокалипсис сегодня",
                                             "Гарри Поттер и философский камень", "Железный человек",
                                             "Капитан Америка: Первый мститель", "Тёмный рыцарь",
                                             "Терминатор", "Куб"))

        val id = ArrayList<String> (listOf("348", "1895", "152", "28", "671", "1726", "1771", "155", "218", "431"))
        val year = ArrayList<String> (listOf("1979", "2005", "1979", "1977", "2001", "2008", "2011", "2008", "1984", "1997"))
        val rating = ArrayList<String> (listOf("8.1", "7.3", "6.4", "8.2", "7.9", "7.6", "6.9", "8.5", "7.6", "6.8"))

        for (i in name.indices) {
            fantasticFilms.add(ItemFilm(name[i], id[i], year[i], rating[i]))
        }
        Handler().post{onFilmReadyCallback.onDataReady(fantasticFilms)}
    }

    fun getHorrorFilms(onFilmReadyCallback: OnFilmReadyCallback) {
        val name = ArrayList<String> (listOf("Пила. Игра на выживание", "Сияние",
                                             "28 дней спустя", "Рассвет мертвецов",
                                             "Бегущий по лезвию", "Ведьма из Блэр: Курсовая с того света",
                                             "Омен", "Звонок",
                                             "Ребёнок Розмари", "Полтергейст"))

        val id = ArrayList<String> (listOf("176", "694", "170", "924", "78", "2667", "806", "565", "805", "609"))
        val year = ArrayList<String> (listOf("2004", "1980", "2002", "2004", "1982", "1999", "2006", "2002", "1968", "1982"))
        val rating = ArrayList<String> (listOf("7.4", "8.2", "7.2", "7.0", "7.9", "6.3", "5.5", "6.6", "7.8", "7.1"))

        for (i in name.indices) {
            horrorFilms.add(ItemFilm(name[i], id[i], year[i], rating[i]))
        }
        Handler().post{onFilmReadyCallback.onDataReady(horrorFilms)}
    }
}

interface OnFilmReadyCallback {
    fun onDataReady(data : ArrayList<ItemFilm>)
}