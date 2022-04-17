package tw.edu.pu.csim.tcyang.ghost

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import tw.edu.pu.csim.tcyang.ghost.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnTouchListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txv.text = "拖曳及碰撞偵測"
        binding.img1.setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        binding.txv.text = "觸控"
        if (event?.action == MotionEvent.ACTION_MOVE){
            v?.x = event.rawX - v!!.width/2
            v?.y = event.rawY - v!!.height/2

            var r1: Rect = Rect(v.x.toInt(), v.y.toInt(),
                v.x.toInt() + v.width, v.y.toInt() + v.height)
            var r2: Rect = Rect(binding.img2.x.toInt(), binding.img2.y.toInt(),
                binding.img2.x.toInt() + binding.img2.width,
                binding.img2.y.toInt() + binding.img2.height)

            if(r1.intersect(r2)) {
                binding.txv.text = "碰撞"
            }
            else{
                binding.txv.text = ""
            }
        }
        return true
    }
}