package com.example.autoservice.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity

public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Column
    private int rating;
    @Column
    private String comment;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date review_date = new Date();

    public Reviews() {
    }

    public Long getId() {
        return this.id;
    }

    public Users getUsers() {
        return this.users;
    }

    public int getRating() {
        return this.rating;
    }

    public String getComment() {
        return this.comment;
    }

    public Date getReview_date() {
        return this.review_date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Reviews)) return false;
        final Reviews other = (Reviews) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$users = this.getUsers();
        final Object other$users = other.getUsers();
        if (this$users == null ? other$users != null : !this$users.equals(other$users)) return false;
        if (this.getRating() != other.getRating()) return false;
        final Object this$comment = this.getComment();
        final Object other$comment = other.getComment();
        if (this$comment == null ? other$comment != null : !this$comment.equals(other$comment)) return false;
        final Object this$review_date = this.getReview_date();
        final Object other$review_date = other.getReview_date();
        if (this$review_date == null ? other$review_date != null : !this$review_date.equals(other$review_date))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Reviews;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $users = this.getUsers();
        result = result * PRIME + ($users == null ? 43 : $users.hashCode());

        result = result * PRIME + this.getRating();
        final Object $comment = this.getComment();
        result = result * PRIME + ($comment == null ? 43 : $comment.hashCode());
        final Object $review_date = this.getReview_date();
        result = result * PRIME + ($review_date == null ? 43 : $review_date.hashCode());
        return result;
    }

    public String toString() {
        return "Reviews(id=" + this.getId() + ", users=" + this.getUsers() + ", rating=" + this.getRating() + ", comment=" + this.getComment() + ", review_date=" + this.getReview_date() + ")";
    }
}
