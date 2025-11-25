import React from 'react';
import { ShieldCheck } from 'lucide-react';

interface TrustBlockProps {
  message: string;
}

export function TrustBlock({ message }: TrustBlockProps) {
  return (
    <div className="bg-blue-50 border-2 border-[#3498DB] rounded-lg p-4 flex items-start gap-3">
      <ShieldCheck className="w-6 h-6 text-[#3498DB] flex-shrink-0 mt-0.5" />
      <p className="text-gray-700 flex-1">{message}</p>
    </div>
  );
}
