import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import Input from "./Input";
import useCurrentUser from "Hooks/useCurrentUser";
import api from "api/api";
export const SignIn = ({ toggleIsSignIn }) => {
  const history = useHistory();
  const { setCurrentUser } = useCurrentUser();

  const [info, setInfo] = useState({
    userId: "",
    password: "",
  });

  const onChange = (e) => {
    const {
      target: { value, id },
    } = e;

    setInfo({ ...info, [id]: value });
  };
  const onSubmit = () => {
    console.log(info.userId);
    console.log(info.password);
    setCurrentUser(info.userId); //이건 프론트딴에서 memberId이 들어왔다고 하는거...
    api.post("user/login",null , {
        params:{
          'userId': info.userId, //인터넷에 api post쳐봐서 이런식으로 보내면 된다고 했는데...
          'password': info.password,
        }})
        .then((response) => {
          console.log(response);
        });
    history.push("/");
  };
  return (
    <div className="auth">
      <Input
        id="userId"
        placeholder="userId"
        value={info.userId}
        onChange={onChange}
      />

      <Input
        id="password"
        placeholder="Password"
        value={info.password}
        onChange={onChange}
        type="password"
      />
      <button onClick={onSubmit}>로그인하기</button>
      <span className="auth__noti">
        회원가입하시겠습니까?<strong onClick={toggleIsSignIn}>회원가입</strong>
      </span>
    </div>
  );
};
