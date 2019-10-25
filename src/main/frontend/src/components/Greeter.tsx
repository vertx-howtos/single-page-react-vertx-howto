import React from 'react';
import axios, { AxiosResponse } from 'axios';

type GreeterState = { message: string };

class Greeter extends React.Component<any, GreeterState> { // <1>
  constructor(props: any) {
    super(props);
    this.state = { // <2>
      message: 'Default message',
    };
  }

  componentDidMount() { // <4>
    this.getMessages();
  }

  private async getMessages() {
    const messages: AxiosResponse<string> = await axios.get('/api/message', { responseType: 'text' });
    this.setState({ message: messages.data });
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
