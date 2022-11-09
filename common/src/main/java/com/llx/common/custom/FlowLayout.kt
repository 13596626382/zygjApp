package com.llx.common.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.llx.common.R
import com.llx.common.room.SearchBean

/**
 * 文字流式布局
 */
class FlowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    // 水平间距，单位为dp
    private var horizontalSpacing = dp2px(15f)

    // 垂直间距，单位为dp
    private var verticalSpacing = dp2px(15f)

    // 行的集合
    private val lines: MutableList<Line> = ArrayList()

    // 当前的行
    private var line: Line? = null

    // 当前行使用的空间
    private var lineSize = 0

    // 关键字大小，单位为sp
    private var textSize = sp2px(15f)

    // 关键字颜色
    private var textColor = Color.BLACK

    // 关键字背景
    private var backgroundResource = R.drawable.bg_frame

    // 关键字水平padding，单位为dp
    private var textPaddingH = dp2px(13f)

    // 关键字垂直padding，单位为dp
    private var textPaddingV = dp2px(5f)

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.FlowLayoutAttrs, defStyleAttr, 0
        )
        val count = typedArray.indexCount
        for (i in 0 until count) {
            val attr = typedArray.getIndex(i)
            when (attr) {
                R.styleable.FlowLayoutAttrs_horizontalSpacing -> {
                    horizontalSpacing = typedArray.getDimensionPixelSize(attr, dp2px(10f))
                }
                R.styleable.FlowLayoutAttrs_verticalSpacing -> {
                    verticalSpacing = typedArray.getDimensionPixelSize(attr, dp2px(10f))
                }
                R.styleable.FlowLayoutAttrs_itemSize -> {
                    textSize = typedArray.getDimensionPixelSize(attr, sp2px(15f))
                }
                R.styleable.FlowLayoutAttrs_itemColor -> {
                    textColor = typedArray.getColor(attr, Color.BLACK)
                }
                R.styleable.FlowLayoutAttrs_backgroundResource -> {
                    backgroundResource = typedArray.getResourceId(attr, R.drawable.bg_frame)
                }
                R.styleable.FlowLayoutAttrs_textPaddingH -> {
                    textPaddingH = typedArray.getDimensionPixelSize(attr, dp2px(7f))
                }
                R.styleable.FlowLayoutAttrs_textPaddingV -> {
                    textPaddingV = typedArray.getDimensionPixelSize(attr, dp2px(4f))
                }
            }
        }
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 实际可以用的宽和高
        val width = MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        val height = MeasureSpec.getSize(heightMeasureSpec) - paddingBottom - paddingTop
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        // Line初始化
        restoreLine()
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            // 测量所有的childView
            val childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                width,
                if (widthMode == MeasureSpec.EXACTLY) MeasureSpec.AT_MOST else widthMode
            )
            val childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                if (heightMode == MeasureSpec.EXACTLY) MeasureSpec.AT_MOST else heightMode
            )
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec)
            if (line == null) {
                // 创建新一行
                line = Line()
            }

            // 计算当前行已使用的宽度
            val measuredWidth = child.measuredWidth
            lineSize += measuredWidth

            // 如果使用的宽度小于可用的宽度，这时候childView能够添加到当前的行上
            if (lineSize <= width) {
                line!!.addChild(child)
                lineSize += horizontalSpacing
            } else {
                // 换行
                newLine()
                line!!.addChild(child)
                lineSize += child.measuredWidth
                lineSize += horizontalSpacing
            }
        }

        // 把最后一行记录到集合中
        if (line != null && !lines.contains(line)) {
            lines.add(line!!)
        }
        var totalHeight = 0
        // 把所有行的高度加上
        for (i in lines.indices) {
            totalHeight += lines[i].height
        }
        // 加上行的竖直间距
        totalHeight += verticalSpacing * (lines.size - 1)
        // 加上上下padding
        totalHeight += paddingBottom
        totalHeight += paddingTop

        // 设置自身尺寸
        // 设置布局的宽高，宽度直接采用父view传递过来的最大宽度，而不用考虑子view是否填满宽度
        // 因为该布局的特性就是填满一行后，再换行
        // 高度根据设置的模式来决定采用所有子View的高度之和还是采用父view传递过来的高度
        setMeasuredDimension(
            MeasureSpec.getSize(widthMeasureSpec),
            resolveSize(totalHeight, heightMeasureSpec)
        )
    }

    private fun restoreLine() {
        lines.clear()
        line = Line()
        lineSize = 0
    }

    private fun newLine() {
        // 把之前的行记录下来
        if (line != null) {
            lines.add(line!!)
        }
        // 创建新的一行
        line = Line()
        lineSize = 0
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        val left = paddingLeft
        var top = paddingTop
        for (i in lines.indices) {
            val line = lines[i]
            line.layout(left, top)
            // 计算下一行的起点y轴坐标
            top += line.height + verticalSpacing
        }
    }

    /**
     * 管理每行上的View对象
     */
    internal inner class Line {
        // 子控件集合
        private val children: MutableList<View> = ArrayList()

        // 行高
        var height = 0

        /**
         * 添加childView
         *
         * @param childView 子控件
         */
        fun addChild(childView: View) {
            children.add(childView)

            // 让当前的行高是最高的一个childView的高度
            if (height < childView.measuredHeight) {
                height = childView.measuredHeight
            }
        }

        /**
         * 指定childView的绘制区域
         *
         * @param left 左上角x轴坐标
         * @param top  左上角y轴坐标
         */
        fun layout(left: Int, top: Int) {
            val totalWidth = measuredWidth - paddingLeft - paddingRight
            // 当前childView的左上角x轴坐标
            var currentLeft = left
            for (i in children.indices) {
                val view = children[i]
                // 指定childView的绘制区域
                view.layout(
                    currentLeft, top, currentLeft + view.measuredWidth,
                    top + view.measuredHeight
                )
                // 计算下一个childView的位置
                currentLeft += view.measuredWidth + horizontalSpacing
            }
        }

        val childCount: Int
            get() = children.size
    }

    /**
     * 设置关键字标签
     *
     * @param list                关键字
     * @param onItemClickListener 点击监听
     */
    @Deprecated("")
    fun setFlowLayout(list: List<SearchBean>, onItemClickListener: OnItemClickListener) {
        removeAllViews()
        addViews(list, onItemClickListener)
    }

    /**
     * 设置关键字标签
     *
     * @param str                 关键字
     * @param onItemClickListener 点击监听
     */
    fun setView(str: SearchBean, onItemClickListener: OnItemClickListener) {
        val list: MutableList<SearchBean> = ArrayList()
        list.add(str)
        setViews(list, onItemClickListener)
    }

    /**
     * 设置关键字标签
     *
     * @param list                关键字
     * @param onItemClickListener 点击监听
     */
    fun setViews(list: List<SearchBean>, onItemClickListener: OnItemClickListener) {
        removeAllViews()
        addViews(list, onItemClickListener)
    }

    /**
     * 增加关键字标签
     *
     * @param str                 关键字
     * @param onItemClickListener 点击监听
     */
    fun addView(str: SearchBean, onItemClickListener: OnItemClickListener) {
        val list: MutableList<SearchBean> = ArrayList()
        list.add(str)
        addViews(list, onItemClickListener)
    }

    /**
     * 增加关键字标签
     *
     * @param list                关键字
     * @param onItemClickListener 点击监听
     */
    private fun addViews(list: List<SearchBean>, onItemClickListener: OnItemClickListener) {
        for (i in list.indices) {
            val tv = TextView(context)

            // 设置TextView属性
            tv.text = list[i].content
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
            tv.setTextColor(textColor)
            tv.gravity = Gravity.CENTER
            tv.setPadding(textPaddingH, textPaddingV, textPaddingH, textPaddingV)
            tv.isClickable = true
            tv.setBackgroundResource(backgroundResource)
            addView(
                tv, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
            tv.setOnClickListener { onItemClickListener.onItemClick(tv.text.toString()) }
        }
    }

    /**
     * 点击事件监听
     */
    fun interface OnItemClickListener {
        fun onItemClick(content: String)
    }

    /**
     * 设置文字水平间距
     *
     * @param horizontalSpacing 间距/dp
     */
    fun setHorizontalSpacing(horizontalSpacing: Int) {
        this.horizontalSpacing = dp2px(horizontalSpacing.toFloat())
    }

    /**
     * 设置文字垂直间距
     *
     * @param verticalSpacing 间距/dp
     */
    fun setVerticalSpacing(verticalSpacing: Int) {
        this.verticalSpacing = dp2px(verticalSpacing.toFloat())
    }

    /**
     * 设置文字大小
     *
     * @param textSize 文字大小/sp
     */
    fun setTextSize(textSize: Int) {
        this.textSize = sp2px(textSize.toFloat())
    }

    /**
     * 设置文字颜色
     *
     * @param textColor 文字颜色
     */
    fun setTextColor(textColor: Int) {
        this.textColor = textColor
    }

    /**
     * 设置文字背景
     *
     * @param backgroundResource 文字背景
     */
    override fun setBackgroundResource(backgroundResource: Int) {
        this.backgroundResource = backgroundResource
    }

    /**
     * 设置文字水平padding
     *
     * @param textPaddingH padding/dp
     */
    fun setTextPaddingH(textPaddingH: Int) {
        this.textPaddingH = dp2px(textPaddingH.toFloat())
    }

    /**
     * 设置文字垂直padding
     *
     * @param textPaddingV padding/dp
     */
    fun setTextPaddingV(textPaddingV: Int) {
        this.textPaddingV = dp2px(textPaddingV.toFloat())
    }

    /**
     * dp转px
     *
     * @param dp dp值
     * @return px值
     */
    private fun dp2px(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            resources.displayMetrics
        ).toInt()
    }

    /**
     * sp转px
     *
     * @param sp sp值
     * @return px值
     */
    private fun sp2px(sp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, sp,
            resources.displayMetrics
        ).toInt()
    }
}