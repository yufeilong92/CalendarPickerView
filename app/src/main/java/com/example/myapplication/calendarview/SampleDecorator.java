package com.example.myapplication.calendarview;

import android.view.View;

import com.example.myapplication.R;

import java.util.Calendar;
import java.util.Date;

public class SampleDecorator implements CalendarCellDecorator {

    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        //设置不可选元素隐藏
        setSelectableItemShowOrHide(cellView);
        //设置当前item文字颜色
        setCellTextStyle(cellView);
        //设置当前文字
        setCellText(cellView);
    }

    private void setSelectableItemShowOrHide(CalendarCellView cellView) {
        if (!cellView.isSelectable()) {  //如果不可選
            if (cellView.isCurrentMonth()) {
                cellView.setVisibility(View.VISIBLE);
            } else {
                cellView.setVisibility(View.INVISIBLE);
            }
        } else {
            cellView.setVisibility(View.VISIBLE);
        }
    }

    private void setCellTextStyle(CalendarCellView cellView){
        Calendar cal = Calendar.getInstance();
        cal.setTime(cellView.getDate());
        if(cellView.isSelectable()){//可选
            if(cellView.isSelected()){//选中
                cellView.setCellTextColor(cellView.getContext().getResources().getColor(R.color.white));
            }else{
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {//周末
                    cellView.setCellTextColor(cellView.getContext().getResources().getColor(R.color.text_main_blue));
                }else{
                    cellView.setCellTextColor(cellView.getContext().getResources().getColor(R.color.text_main_black));
                }
            }
        }else{//不可选
            cellView.setCellTextColor(cellView.getContext().getResources().getColor(R.color.text_hint_gray));
        }
    }

    private void setCellText(CalendarCellView cellView) {
        //只有日子16  有说明或者节日14  说明还带节日12
        String dateString = Integer.toString(cellView.getDate().getDate());
        if (cellView.getDayFlag()==1) {
            cellView.getDayOfMonthTextView().setText(dateString + "\n今天");
            cellView.getDayOfMonthTextView().setTextSize(14);
        } else if (cellView.getDayFlag()==2) {
            cellView.getDayOfMonthTextView().setText(dateString + "\n明天");
            cellView.getDayOfMonthTextView().setTextSize(14);
        } else if (cellView.getDayFlag()==3) {
            cellView.getDayOfMonthTextView().setText(dateString + "\n后天");
            cellView.getDayOfMonthTextView().setTextSize(14);
        } else {
            cellView.getDayOfMonthTextView().setText(dateString);
            cellView.getDayOfMonthTextView().setTextSize(16);
        }
    }

}
