package com.central.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Array
import com.central.screens.GameObj

class Background(private val layers: Array<Texture>) : Actor() {

    private var scroll = 0f
    private val speedDifference = 200

    private var srcX = 0
    private var srcY = 0
    private var flipX = false
    private var flipY = false

    var speed = 0f

    init {
        for (i in 0 until layers.size) {
            layers.get(i).setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge)
        }

        width = GameObj.stg.width
        height = GameObj.stg.height
        scaleY = 1f
        scaleX = scaleY
    }

    override fun act(delta: Float) {
        //this.speed = if (!GameObj.paused) (GameObj.gm.player.vel.x * 0.001).toFloat() else 0f
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        scroll += speed
        for (i in 0 until layers.size) {
            srcX = (scroll + i.toFloat() * this.speedDifference.toFloat() * scroll).toInt()
            batch.draw(layers[i], x, y, originX, originY, width, height,
                    scaleX, scaleY, rotation, srcX, srcY,
                    layers[i].width, layers[i].height, flipX, flipY
            )
        }
    }
}