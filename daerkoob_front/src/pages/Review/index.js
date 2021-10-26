import React, { useState, useEffect } from "react";
import { useHistory, useLocation } from "react-router-dom";
import "./index.scss";
const Review = () => {
  const history = useHistory();
  const location = useLocation();

  const [kind, setKind] = useState("필사");
  useEffect(() => {
    const { pathname } = location;
    if (pathname === "/review") setKind("리뷰");
    else if (pathname === "/transcription") setKind("필사");
    else history.push("/");
  }, [history, location]);
  return (
    <div className="review">
      <h1>종류</h1>
      {kind}
    </div>
  );
};

export default Review;
