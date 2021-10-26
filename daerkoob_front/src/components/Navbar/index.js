import React from "react";
import { Link } from "react-router-dom";
import "./index.scss";
import useCurrentUser from "Hooks/useCurrentUser";
const Navbar = () => {
  // const activeStyle = {
  //   background: "black",
  //   color: "white",
  // };
  const { currentUser, setCurrentUser } = useCurrentUser();

  return (
    <div className="nav">
      <div className="nav__left">
        <Link to="/">home</Link>
      </div>
      <div className="nav__rightBottom">
        <div>
          <Link to="/transcription">리뷰</Link>
        </div>
        <div>
          <Link to="/notice">공지사항</Link>
        </div>
        <div>
          <Link to="/mypage"> 마이페이지</Link>
        </div>
      </div>
      {currentUser ? (
        <div className="nav__rightTop">
          <div>
            <button onClick={() => setCurrentUser(null)}> 로그아웃</button>
          </div>
          <div>{currentUser}</div>
        </div>
      ) : (
        <div className="nav__rightTop">
          <button>
            <Link to="/auth"> 로그인</Link>
          </button>
        </div>
      )}
    </div>
  );
};

export default Navbar;
