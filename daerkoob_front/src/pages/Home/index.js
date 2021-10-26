import React from "react";
import "pages/Home/index.scss";
import HomeCard from "components/Card/HomeCard";
import BookCard from "components/Card/BookCard";

const Home = () => {
  const img_link =
    "https://resource.grapplet.com/marketplace/7176/1591667231081/i.svg.preview.580x870.png";
  return (
    <div className="wrapper">
      <div className="wrapper__banner">배너</div>
      <div className="wrapper__container">
        <HomeCard name="전체 리뷰수" />
        <HomeCard name="전체 필사수" />
        <HomeCard name="리뷰된 책 수" />
      </div>
      <div className="wrapper__container">
        <BookCard title="해리포터" image={img_link} />
        <BookCard title="반지의제왕" image={img_link} />
        <BookCard title="헝거게임" image={img_link} />
        <BookCard title="나니아연대기" image={img_link} />
        <BookCard title="트와일라잇" image={img_link} />
      </div>{" "}
    </div>
  );
};

export default Home;
