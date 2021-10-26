import React from "react";

const Input = ({ id, placeholder, value, onChange, type }) => {
  return (
    <div className="auth__inputWrapper">
      <input
        className="auth__inputWrapper__input"
        requierd
        id={id}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        type={type}
      />
      <span className="auth__inputWrapper__placeholder">{placeholder}</span>
    </div>
  );
};

export default Input;
