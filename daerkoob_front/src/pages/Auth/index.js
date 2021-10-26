import { useEffect, useState } from "react";
import { useHistory, useLocation } from "react-router-dom";
import { SignIn } from "components/Auth/SignIn";
import { SignUp } from "components/Auth/SignUp";
import "pages/Auth/index.scss";
// import Header from "components/Auth/Header";
// import Main from "components/Auth/Main";
// import Layout from "components/Common/Layout";

const Auth = () => {
  const history = useHistory();
  const location = useLocation();

  const [kind, setKind] = useState("로그인");
  const [isSignIn, setIsSignIn] = useState(true);
  const toggleIsSignIn = () => {
    setIsSignIn(!isSignIn);
  };

  // useEffect(() => {
  //   const { pathname } = location;
  //   if (pathname === "/signup") setKind("회원가입");
  //   else if (pathname === "/login") setKind("로그인");
  //   else history.push("/");
  // }, [history, location]);

  return (
    <div className="sign">
      {isSignIn ? (
        <SignIn toggleIsSignIn={toggleIsSignIn} />
      ) : (
        <SignUp toggleIsSignIn={toggleIsSignIn} />
      )}
    </div>
  );
};

export default Auth;
