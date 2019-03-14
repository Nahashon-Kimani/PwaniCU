package com.peter.adapter;

public class NoticeContract {
    //category will be used to differentiate between events and notices.
    private String noticeTitle, noticeDescription, eventDate, inputDate, category;

    public NoticeContract() {
    }

    public NoticeContract(String noticeTitle, String noticeDescription, String eventDate, String inputDate, String category) {
        this.noticeTitle = noticeTitle;
        this.noticeDescription = noticeDescription;
        this.eventDate = eventDate;
        this.inputDate = inputDate;
        this.category = category;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDescription() {
        return noticeDescription;
    }

    public void setNoticeDescription(String noticeDescription) {
        this.noticeDescription = noticeDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
