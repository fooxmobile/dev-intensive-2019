package ru.skillbranch.devintensive.models


import ru.skillbranch.devintensive.R


/*
 * Created by fooxer on 08.08.2020
 */

class Sheldon (var status: STATUS = STATUS.NORMAL, var question: QUESTION = QUESTION.NAME) {

    fun askQuestion(): String {
        return question.text
    }

    /*Требования к методу:
    При вводе верного ответа изменить текущий вопрос на следующий вопрос (question = question.nextQuestion()) и вернуть "Отлично - ты справился\n${question.question}" to status.color
    Если вопросы закончились (Question.IDLE), вернуть "Отлично - ты справился\nНа этом все, вопросов больше нет"
    Необходимо сохранять состояние экземпляра класса Bender при пересоздании Activity (достаточно сохранить Status, Question)

     */

    fun checkAnswer(answer: String) :Pair<String?, Triple<Int,Int,Int>> {
        return if (question.answers.contains((answer.toLowerCase())))  {
            question = question.nextQuestion()
            "Отлично! Это правильный ответ" to status.color
        } else {
            status = status.nextStatus()
            "Это неправильный ответ! Еще раз:\n${question.text}" to status.color
        }
    }



    enum class STATUS(val color: Triple<Int,Int,Int>) {
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(251,255,0)),
        CRITICAL(Triple(255,153,0)),
        DANGER(Triple(255,0,0));

        fun nextStatus() : STATUS {
            return if (this.ordinal < values().lastIndex)
                    values()[ordinal+1]
                else
                    values()[0]
        }
    }

    enum class QUESTION(val text: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("шелдон", "шелдон купер")) {
            override fun nextQuestion(): QUESTION {
                return PROF
            }
        },
        PROF("Каков мой род занятий?", listOf("физик", "физик-теоретик")){
            override fun nextQuestion(): QUESTION {
                return SUPERHERO
            }
        },
        SUPERHERO("В какого супергероя стоит одеться на Хэллоуин?", listOf("флэш", "флеш")){
            override fun nextQuestion(): QUESTION {
                return TEA
            }
        },
        TEA("Если гость расстроен, какой напиток ему следует предложить?", listOf("чай", "ромашковый чай")){
            override fun nextQuestion(): QUESTION {
                return NEIGHBOUR
            }
        },
        NEIGHBOUR("Как зовут моего соседа?", listOf("леонард", "леонард хоффстедер")){
            override fun nextQuestion(): QUESTION {
                return BAZINGA
            }
        },
        BAZINGA("Каким словом нужно закончить шутку?", listOf("бугагашенька", "bazinga")){
            override fun nextQuestion(): QUESTION {
                return IDLE
            }
        },
        IDLE("У меня больше нет вопросов", listOf()) {
            override fun nextQuestion(): QUESTION {
                return IDLE
            }
        };

        abstract fun nextQuestion() : QUESTION
    }

}