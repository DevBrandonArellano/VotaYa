import React from 'react';
import { Shield } from 'lucide-react';

interface SecurityAlertProps {
  message: string;
}

export function SecurityAlert({ message }: SecurityAlertProps) {
  return (
    <div className="bg-gradient-to-r from-[#3498DB] to-[#2980B9] text-white p-4 rounded-lg shadow-lg flex items-start gap-3 mb-6">
      <Shield className="w-6 h-6 flex-shrink-0 mt-0.5" />
      <p className="flex-1">{message}</p>
    </div>
  );
}
