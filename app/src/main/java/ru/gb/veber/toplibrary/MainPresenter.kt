package ru.gb.veber.toplibrary

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClick(id: Int) {
        when (id) {
            R.id.buttonOne -> {
                view.setButtonText(0, model.next(0).toString())
            }
            R.id.buttonTwo -> {
                view.setButtonText(1, model.next(1).toString())

            }
            R.id.buttonThree -> {
                view.setButtonText(2, model.next(2).toString())
            }
        }
    }
}