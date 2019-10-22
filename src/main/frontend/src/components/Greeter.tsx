import React from 'react';

export class Greeter extends React.Component<any, { message: string }> { // <1>

  constructor(props: any) {
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
        <span>Message: {this.state.message}</span>
      </div>
    );
  }
}
