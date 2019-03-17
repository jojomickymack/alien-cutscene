package com.central

import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.central.assets.Images
import com.central.assets.Skins
import com.central.assets.Sounds
import com.central.assets.Tunes
import com.central.screens.Game
import com.central.screens.Loading
import ktx.app.KtxGame

class App : KtxGame<Screen>() {

    val skinsManager = AssetManager()
    val textureManager = AssetManager()
    val soundsManager = AssetManager()
    val tunesManager = AssetManager()

    override fun create() {
        AppObj.app = this

        Skins.manager = skinsManager
        Images.manager = textureManager
        Sounds.manager = soundsManager
        Tunes.manager = tunesManager

        /**
         * since this game doesn't have a lot to load, it's ok to load everything upfront
         * with more assets you'd want to strategically call load() only on what needs to be
         * ready for the next scene and unload the stuff you're done with
         *
         * the skin should be loaded first because it's used in the loading screen
         */
        Skins.values().forEach { it.load() }

        /**
         * load the skin before proceeding, it's needed for that label
         */
        while (!skinsManager.update()) skinsManager.update()

        Images.values().forEach { it.load() }
        Sounds.values().forEach { it.load() }
        Tunes.values().forEach { it.load() }

        val loading = Loading(this)
        addScreen(loading)
        setScreen<Loading>()
    }

    override fun dispose() {
        skinsManager.dispose()
        textureManager.dispose()
        soundsManager.dispose()
        tunesManager.dispose()
        println("all assets disposed")
        super.dispose()
    }
}
