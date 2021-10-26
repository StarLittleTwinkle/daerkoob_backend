import React from "react";

const BookCard = ({ image, title }) => {
  return (
    <div className="bookCard">
      <img src={image} alt="" />
      {title}
    </div>
  );
};

export default BookCard;
