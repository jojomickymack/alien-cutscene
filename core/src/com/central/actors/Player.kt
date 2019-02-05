package com.central.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.central.screens.GameObj


class State

class PlayerStates {
    companion object States {
        val standing = State()
        val walking = State()
    }
}

class Player : Actor() {
    val sprite = Sprite()
    private val tex = Texture("capguy_walk.png")
    internal var walkSheet = TextureRegion(tex, 0, 0, tex.width, tex.height)
    internal var currentFrame: TextureRegion

    val h = 150f
    val w = 200f
    val vel = Vector2(0f, 0f)
    val mapPos = Vector2(0f, 0f)
    var rect = Rectangle()
    var goLeft = false
    var goRight = false
    var facesRight = true
    val maxVel = 5f
    private val damping = Vector2(0.9f, 0.9f)
    var xDiff = 0f

    var stateTime = 0f
    var state: State = PlayerStates.standing

    val regions: Array<TextureRegion> = walkSheet.split(187, 324)[0]
    val stand: Animation<TextureRegion> = Animation(0F, regions[0])
    val walk: Animation<TextureRegion> = Animation(0.15f, regions[1], regions[2], regions[3], regions[4], regions[5], regions[6], regions[7], regions[8])

    init {
        this.sprite.setPosition(GameObj.stg.width / 2 - w / 2, GameObj.stg.height / 2 - h / 2)
        this.walk.playMode = Animation.PlayMode.LOOP
        this.currentFrame = this.walk.getKeyFrame(this.stateTime, true) as TextureRegion
        this.sprite.setSize(h, w)
    }

    private fun handleInput() {
        if (this.goLeft) {
            this.facesRight = false
            this.vel.x = -this.maxVel
        }
        else if (this.goRight) {
            this.facesRight = true
            this.vel.x = this.maxVel
        }
    }

    override fun act(delta: Float) {
        this.currentFrame = when (state) {
            PlayerStates.standing -> this.stand.getKeyFrame(this.stateTime)
            PlayerStates.walking -> this.walk.getKeyFrame(this.stateTime)
            else -> this.stand.getKeyFrame(this.stateTime)
        }
        this.sprite.setRegion(this.currentFrame)

        val lastX = this.vel.x

        var deltaTime = delta
        if (deltaTime == 0f) return

        if (deltaTime > 0.1f)
            deltaTime = 0.1f

        this.stateTime += deltaTime

        this.vel.scl(this.damping)

        handleInput()

        if (Math.abs(this.vel.x) > 3) {
            this.state = PlayerStates.walking
        } else if (Math.abs(this.vel.x) < 3) {
            this.vel.x = 0f
            this.state = PlayerStates.standing
        }

        xDiff = this.vel.x - lastX
        this.mapPos.x += this.vel.x * GameObj.unitScale
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        if (this.facesRight) {
            sprite.setFlip(false, false)
        } else {
            sprite.setFlip(true, false)
        }
        sprite.draw(batch)
    }

}