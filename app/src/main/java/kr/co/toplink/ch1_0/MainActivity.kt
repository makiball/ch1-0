package kr.co.toplink.ch1_0

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

import kr.co.toplink.ch1_0.transition.RotationTransition
import kr.co.toplink.ch1_0.transition.ScaleTransition
import kr.co.toplink.ch1_0.transition.TextColorTransition

import kr.co.toplink.ch1_0.databinding.ActivityMainBinding
import kr.co.toplink.ch1_0.transition.AlphaTransition
import kr.co.toplink.ch1_0.transition.BackgroundColorTransition

class MainActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Ch1-0 맞춤전환"

        /* 크기조정 x 좌표와 y 좌표를 실수로 받아와서 크기를 조정한다. */
        binding.button1.apply {
            setOnClickListener {

                val transitions = TransitionSet()

                val transition = ScaleTransition(
                    0f,
                    0f,
                    1f,
                    1f,
                    forceValues = true
                ).apply {
                    duration = 3000
                    addTarget(binding.view1)
                }

                Toast.makeText(
                    applicationContext,
                    "${transition::class.java.simpleName}",
                    Toast.LENGTH_SHORT
                ).show()

                val colorTransition =
                    createTextColorChangeTransition(binding.tvTvView1, Color.GREEN, Color.RED)

                transitions.addTransition(transition)
                transitions.addTransition(colorTransition)

                TransitionManager.beginDelayedTransition(binding.constraintLayout, transitions)
            }
        }

        binding.button2.apply {
            setOnClickListener{
                val transitions = TransitionSet()
                val transition = AlphaTransition(0f, 1f, true).apply {
                    addTarget(binding.view2)
                    duration = 3000
                }

                Toast.makeText(
                    applicationContext,
                    "${transition::class.java.simpleName}",
                    Toast.LENGTH_SHORT
                ).show()

                val colorTransition = createTextColorChangeTransition(binding.tvTvView2, Color.GREEN, Color.RED)

                transitions.addTransition(transition)
                transitions.addTransition(colorTransition)

                TransitionManager.beginDelayedTransition(binding.constraintLayout, transitions)
            }
        }

        binding.button3.apply {
            setOnClickListener{

                val transitions = TransitionSet()

                val transition = RotationTransition(
                    -360f,
                    0f,
                    true
                ).apply {
                    addTarget(binding.view3)
                    duration = 3000
                }

                Toast.makeText(applicationContext, "${transition::class.java.simpleName}", Toast.LENGTH_SHORT).show()

                val colorTransition = createTextColorChangeTransition(binding.tvTvView3, Color.GREEN, Color.RED)

                transitions.addTransition(transition)
                transitions.addTransition(colorTransition)

                TransitionManager.beginDelayedTransition(binding.constraintLayout, transitions)
            }
        }

        binding.button4.apply {
            setOnClickListener{
                val transitions = TransitionSet()

                val transition = BackgroundColorTransition (
                    Color.YELLOW,
                    Color.BLACK,
                    true
                 ).apply {
                     addTarget(binding.view4)
                    duration = 3000
                }

                Toast.makeText(
                    applicationContext,
                    "${transition::class.java.simpleName}",
                    Toast.LENGTH_SHORT
                ).show()

                val textColorTransition = createTextColorChangeTransition(binding.tvTvView4, Color.GREEN, Color.BLACK)
                transitions.addTransition(transition)
                transitions.addTransition(textColorTransition)
                TransitionManager.beginDelayedTransition(binding.constraintLayout, transitions)

            }
        }

    }

    private fun createTextColorChangeTransition(
        textView: TextView,
        colorStart: Int,
        colorEnd: Int
    ): Transition {
        return TextColorTransition(colorStart, colorEnd, forceValues = true)
            .apply {
                addTarget(textView)
                duration = 3000
            }
    }
}