import React from 'react';

class Greeter extends React.Component<any, { message: string }> { // <1>
  constructor(props: any) {
    super(props);
    this.state = { // <2>
      message: 'Default message',
    };
  }

  componentDidMount() { // <4>
    fetch('/api/message')
      .then((response) => response.text())
      .then((text) => this.setState({ message: text }));
  }

  render() { // <3>
    const { message } = this.state;
    return (
      <div>
        <span>
          Message:
          {' '}
          {message}
        </span>
      </div>
    );
  }
}

export default Greeter;
