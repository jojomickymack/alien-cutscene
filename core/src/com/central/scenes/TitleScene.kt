package com.central.scenes

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.central.App
import com.central.assets.Images.*
import com.central.assets.Sounds.*
import com.central.assets.Skins.*
import ktx.actors.alpha
import ktx.actors.plus
import ktx.actors.plusAssign

class TitleScene(app: App) : Group() {

    init {
        val background = Image(alien_egg())
        val logo = Image(alien_logo())
        var label = Label("In space no one hears you scream.", my_skin(), "transparent")

        // set positions for all of the elements, set alphas to 0 because they fade in as the scene progresses

        logo.setSize(app.stg.width, logo.height)
        logo.setPosition(0f, app.stg.height - logo.height)
        logo.alpha = 0f

        background.setPosition(app.stg.width / 2 - background.width / 2, 0f)
        background.alpha = 0f

        label.setPosition(app.hudStg.width / 2 - label.width / 2, app.hudStg.height / 2 - label.height / 2)
        label.alpha = 0f

        /**
         * each element has it's own sequence of actions, kind of sucks because you need to add up
         * the durations of the actions and delays to get the timing right
         * a sequence is synchronous, meaning that the first action need to be completed before the following
         * one begins, however if you were to nest the actions of different elements by putting them into
         * the Actions.run block, it gets even more confusing because those actions would then be asynchronous
         *
         * probably best to stick with having many short scenes composed of a few actions that don't have complicated timings
         */

        // note that the entire scene is ended by this Actions.run closure the background initiates

        // background appears first, the fade in takes 2 seconds, it then waits 4, then fades out in 2
        // it ends the scene after a total of 8 seconds

        background += sequence(Actions.run { suspense().setVolume(suspense().play(), 0.5f) } + fadeIn(2f) + delay(4f) + fadeOut(2f) +
                Actions.run {
                    app.stg.clear()
                    app.stg.addActor(StoryScene1(app))
                }
        )

        // logo waits while the background fades in, sequence still adds up to 8

        logo += sequence(delay(2f) + fadeIn(2f) + delay(2f) + fadeOut(2f))

        // label waits while background and logo fade in, then fades in while the others are delayed and fades out
        // again, the sequence adds up to 8

        label += sequence(delay(4f) + fadeIn(2f), delay(0f) + fadeOut(2f))

        this += background
        this += logo
        this += label
    }
}