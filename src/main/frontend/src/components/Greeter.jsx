import React from "react";

export class Greeter extends React.Component { // <1>

  constructor(props) {
    super(props);
    this.state = { // <2>
      message: "Default message"
    }
  }

  componentDidMount() { // <4>
    fetch("/api/message")
      .then(response => response.text())
      .then(text => this.setState({message: text}));
  }

  render() { // <3>
    return (
      <div>
        <span>{this.state.message}</span>
      </div>
    );
  }
}
